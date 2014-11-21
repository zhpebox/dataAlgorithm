package com.yang;

import java.lang.String; 
import java.io.PrintStream; 

public class PrefixSpan { 
  private String sequence[]= {"a","abc","ac","d","cf","ad","c","bc","ae","ef","ab","df","c","b","e","g","af","c","b","c"}; 
  private int sequence_count[] = {5,4,5,6}; 
  private String key_fr_seq[]= new String[1000]; 
  private int key_fr_count[]= new int[500]; 
  private int s_tab = 0; 
  private int c_tab = 0; 
  private char f_list [] = new char[20]; 
  private int total=0; 
  private int mincout=0; 
 
  public PrefixSpan(){ 
  //System.out.println("PrefixSpan()"); 
  mincout=2; 
  for(int i=0;i<20;i++){ 
    int j=sequence[i].length(); 
    for(int k=0;k<j;k++){ 
      char m = sequence[i].charAt(k); 
      int n = 0; 
      while(n<total){ 
        if(m==f_list[n]) break; 
        n++; 
      } 
      if(n==total){ 
        f_list[n]=m; 
        total++; 
        } 
  } 
  } 
  } 
 
  public void Count_fr1(){ 
      int sup[]=new int[total]; 
      for(int i=0;i<total;i++){ 
        int ch=f_list[i]; 
        int ch_total=0; 
        int begin=0; 
        for(int j=0;j<4;j++){ 
          int sl=sequence_count[j]; 
          for(int k=begin;k<(begin+sl);k++){ 
            if(sequence[k].indexOf(ch)!=-1){ 
              ch_total++; 
              break; 
              } 
          } 
          begin+=sl; 
        } 
        sup[i]=ch_total; 
        } 
      for (int l=0;l<total;l++){ 
        if(sup[l]<2){ 
          for (int n=l;n<total;n++){ 
            f_list[n]=f_list[n+1]; 
          } 
          total=total-1; 
        } 
        else{ 
          key_fr_seq[l]=String.valueOf(f_list[l]); 
          s_tab++; 
          key_fr_count[l]= 1; 
          c_tab++; 
          } 
        } 
      //for (int y=0;y<total;y++) System.out.println(key_fr_seq[y]); 
  } 
 
  public String replace_1 (String str,int len,int pla) { 
    int i=str.length(); 
    char[] ctt = new char[i-pla]; 
    char[] ct = str.toCharArray(); 
    if(pla!=0){ 
      int j=0; 
      for(int l=pla;l<i;l++) ctt[j++]=ct[l]; 
      for(int ll=0;ll<len;ll++) ctt[ll] = '_'; 
      return String.copyValueOf(ctt); 
      } 
    else{ 
       for(int ll=0;ll<len;ll++) ct[ll] = '_'; 
      return String.copyValueOf(ct); 
    } 
  } 
 
  public String replace (String str,int pla) { 
    int i=str.length(); 
    char[] ctt = new char[i-pla]; 
    char[] ct = str.toCharArray(); 
    if(pla!=0){ 
      int j=0; 
      for(int l=pla;l<i;l++) ctt[j++]=ct[l]; 
      ctt[0] = '_'; 
      return String.copyValueOf(ctt); 
      } 
    else{ 
      ct[0]='_'; 
      return String.copyValueOf(ct); 
    } 
  } 
 
  public int makeout(int t_num,String temp_s,String sd[],int sd_count[]){ 
      int begin=0; 
      int t_mincout=0; 
      int leng=temp_s.length(); 
      for(int i=0;i<t_num;i++){ 
        int j=sd_count[i]; 
        for(int k=begin;k<(begin+j);k++){ 
          int n=0; 
          if(leng==1) 
            if(sd[k].indexOf('_')==-1) 
              n = sd[k].indexOf(temp_s); 
            else n=-1; 
          else { 
            int n1 = sd[k].indexOf(temp_s); 
            if(n1!=-1) n=n1; 
            else{ 
              char[] ct = temp_s.toCharArray(); 
              for(int z=0;z<leng-1;z++) ct[z]='_'; 
                String ss = String.copyValueOf(ct); 
                int n2 = sd[k].indexOf(ss); 
                if(n2!=-1) n=n2; 
                else n=-1; 
                } 
            } 
          if(n!=-1){ 
            t_mincout++; 
            break; 
          } 
        } 
        begin=begin+j; 
      } 
      return t_mincout; 
  } 
 
  public void PrefixSpan(String a[],String sd[],int sd_count[],int t_num,int i_num,int a_num) { 
    for(int t_total=0;t_total<total;t_total++){ 
      //一次循环 
      /////////////////////////////////////////情况一 
      int tnum=0; 
      int inum=0; 
 
      String temp_s=String.valueOf(f_list[t_total]); 
      tnum=makeout(t_num,temp_s,sd,sd_count); 
 
      if(tnum>=mincout){ 
      //开始记录频繁序列 
        for (int i=0;i<a_num;i++) key_fr_seq[s_tab++]=a[i]; 
        key_fr_seq[s_tab++]=temp_s; 
        key_fr_count[c_tab++]=a_num+1; 
      //测试 
      //System.out.println(a[0]+"  "+temp_s); 
      //  for (int i=s_tab-2;i<s_tab;i++) 
      //    System.out.println(key_fr_seq[i]); 
      //  System.out.println(key_fr_count[c_tab-1]); 
      //生成递归所需参数 
 
        PrefixSpan temp=new PrefixSpan(); 
        int sd_count_1[]=new int[tnum]; 
        String sd_1[]=new String[i_num]; 
        int begin=0; 
        int sdc=0; 
        for(int i=0;i<t_num;i++){ 
          int j=sd_count[i]; 
          for(int k=begin;k<(begin+j);k++){ 
            //System.out.println("k: "+k+"  begin: "+begin+"  j: "+j); 
            int templ=0; 
            if(sd[k].indexOf('_')==-1){ 
              int n=sd[k].indexOf(temp_s); 
              if(n!=-1){ 
                if(n==0&&sd[k].length()!=1){ 
                    sd_1[inum++]=temp.replace (sd[k],n); 
                    templ++; 
                  } 
                if(n!=0&&n!=sd[k].length()-1){ 
                  sd_1[inum++]=temp.replace (sd[k],n); 
                  templ++; 
                  } 
                //System.out.println("begin+j "+(begin+j)); 
                for(int t=k+1;t<(begin+j);t++){ 
                  sd_1[inum++]=sd[t]; 
                  templ++; 
                  } 
                sd_count_1[sdc++]=templ; 
                break; 
              } 
            } 
            } 
            begin+=j; 
          } 
           String aa[]=new String[a_num+1]; 
           for(int e=0;e<a_num;e++) aa[e]=a[e]; 
           aa[a_num]=temp_s; 
           //String temp_aa="  "; 
           //for(int e=0;e<(a_num+1);e++) temp_aa=temp_aa+aa[e]+" "; 
           //System.out.println("aa:"+temp_aa); 
           PrefixSpan(aa,sd_1,sd_count_1,tnum,inum,a_num+1); 
           /* 
           //测试 
           System.out.println(key_fr_seq[s_tab-2]+" "+key_fr_seq[s_tab-1]); 
           System.out.println(key_fr_count[c_tab-1]); 
           System.out.println("tnum "+tnum); 
           System.out.println("inum "+inum); 
           int beg=0; 
           for(int u2=0;u2<tnum;u2++){ 
             String temppp="  "; 
             int u0=sd_count_1[u2]; 
             for(int u1=beg;u1<(beg+u0);u1++){ 
               temppp=temppp+sd_1[u1]+" "; 
               } 
             System.out.println(temppp); 
             beg+=u0; 
             }*/ 
         } //if(t_mincout>=mincout) 
      ///////////////////////////////////////////////情况二 
      int tnum_1=0; 
      int inum_1=0; 
      String temp_s_1=a[a_num-1]+String.valueOf(f_list[t_total]); 
      tnum_1=makeout(t_num,temp_s_1,sd,sd_count); 
      if(tnum_1>=mincout){ 
      //开始记录频繁序列 
        for (int i=0;i<a_num-1;i++) key_fr_seq[s_tab++]=a[i]; 
        key_fr_seq[s_tab++]=temp_s_1; 
        key_fr_count[c_tab++]=a_num; 
      //测试 
      //System.out.println(a[0]+"  "+temp_s); 
      //  for (int i=s_tab-2;i<s_tab;i++) 
      //    System.out.println(key_fr_seq[i]); 
      //  System.out.println(key_fr_count[c_tab-1]); 
      //生成递归所需参数 
        PrefixSpan temp=new PrefixSpan(); 
        int sd_count_2[]=new int[tnum_1]; 
        String sd_2[]=new String[i_num]; 
        int begin=0; 
        int sdc=0; 
 
        for(int i=0;i<t_num;i++){ 
          int j=sd_count[i]; 
          for(int k=begin;k<(begin+j);k++){ 
            //System.out.println("k: "+k+"  begin: "+begin+"  j: "+j); 
            int templ=0; 
            int len=temp_s_1.length(); 
            int n=0; 
            char[] cttt = temp_s_1.toCharArray(); 
            for(int z=0;z<len-1;z++) cttt[z]='_'; 
            String temp_s_2 = String.copyValueOf(cttt); 
            if(sd[k].indexOf('_')==-1) n=sd[k].indexOf(temp_s_1); 
            else n=sd[k].indexOf(temp_s_2); 
            if(n!=-1){ 
              if(n==0&&sd[k].length()>len){ 
                    sd_2[inum_1++]=temp.replace_1(sd[k],len,n); 
                    templ++; 
                  } 
                if(n!=0&&n!=sd[k].length()-len){ 
                    sd_2[inum_1++]=temp.replace_1(sd[k],len,n); 
                    templ++; 
                  } 
                //System.out.println("begin+j "+(begin+j)); 
                for(int t=k+1;t<(begin+j);t++){ 
                  sd_2[inum_1++]=sd[t]; 
                  templ++; 
                  } 
                sd_count_2[sdc++]=templ; 
                break; 
              } 
            } 
            begin+=j; 
          } 
           //System.out.println("tnum_1:"+tnum_1); 
           //System.out.println("inum_1:"+inum_1); 
           String aa_1[]=new String[a_num]; 
           for(int e=0;e<a_num-1;e++) aa_1[e]=a[e]; 
           aa_1[a_num-1]=temp_s_1; 
           //String temp_aa="  "; 
           //for(int e=0;e<a_num;e++) temp_aa=temp_aa+aa_1[e]+" "; 
           //System.out.println("aa_1:"+temp_aa); 
           PrefixSpan(aa_1,sd_2,sd_count_2,tnum_1,inum_1,a_num); 
           /* 
           //测试 
           System.out.println(key_fr_seq[s_tab-2]+" "+key_fr_seq[s_tab-1]); 
           System.out.println(key_fr_count[c_tab-1]); 
           System.out.println("tnum "+tnum); 
           System.out.println("inum "+inum); 
           int beg=0; 
           for(int u2=0;u2<tnum;u2++){ 
             String temppp="  "; 
             int u0=sd_count_1[u2]; 
             for(int u1=beg;u1<(beg+u0);u1++){ 
               temppp=temppp+sd_1[u1]+" "; 
               } 
             System.out.println(temppp); 
             beg+=u0; 
             }*/ 
    } //if(tnum_1>=mincout) 
  } 
} 
 
  public static void main(String[] args) { 
     PrefixSpan test = new PrefixSpan(); 
     test.Count_fr1(); 
     int times=0; 
     while(times<test.total){ 
       //获取序列总数tnum 
       int tnum=0;//序列总数 
       int inum=0;//item总数 
       int begin=0; 
       for(int i=0;i<4;i++){ 
         int j=test.sequence_count[i]; 
         for(int k=begin;k<(begin+j);k++){ 
           int n = test.sequence[k].lastIndexOf(test.key_fr_seq[times]); 
           if(n!=-1){ 
             tnum++; 
             break; 
             } 
             } 
         begin+=j; 
       } 
       //生成投影数据库 
       int sd_count[]=new int[tnum];//SD|a的序列数确定 
       int sdc=0; 
       String sd[]=new String[20]; 
       begin=0; 
       for(int i=0;i<4;i++){ 
         int j=test.sequence_count[i]; 
         for(int k=begin;k<(begin+j);k++){ 
           int templ=0; 
           int n = test.sequence[k].indexOf(test.key_fr_seq[times]); 
           if(n!=-1){ 
             if(n==0&&test.sequence[k].length()!=1){ 
                 sd[inum++]=test.replace (test.sequence[k],n); 
                 templ++; 
               } 
             if(n!=0&&n!=test.sequence[k].length()-1){ 
               sd[inum++]=test.replace (test.sequence[k],n); 
               templ++; 
               } 
             for(int t=k+1;t<(begin+j);t++){ 
               sd[inum++]=test.sequence[t]; 
               templ++; 
               } 
             sd_count[sdc++]=templ; 
             break; 
           } 
         } 
         begin+=j; 
       } 
       String aa[]={test.key_fr_seq[times]}; 
       test.PrefixSpan(aa,sd,sd_count,tnum,inum,1); 
       times++; 
     } 
     System.out.println("序列数据库如下："); 
     int t1=0; 
     int beg1=0; 
     while(t1<4){ 
       String sprintt="              ( "; 
       for(int t2=beg1;t2<(beg1+test.sequence_count[t1]);t2++)sprintt=sprintt+test.sequence[t2]+" "; 
       sprintt=sprintt+")"; 
       System.out.println(sprintt); 
       beg1+=test.sequence_count[t1]; 
       t1++; 
     } 
     System.out.println(""); 
     System.out.println(""); 
     System.out.println("执行PrefixSpan算法，生成频繁序列模式结果如下："); 
 
     String tempti=""; 
     int ti=0; 
     int beg=0; 
     for(int u2=0;u2<test.c_tab;u2++){ 
     ti++; 
     String temppp="( "; 
     int u0=test.key_fr_count[u2]; 
     for(int u1=beg;u1<(beg+u0);u1++) temppp=temppp+test.key_fr_seq[u1]+" "; 
     temppp=temppp+")"; 
     int jj=21-temppp.length(); 
     if(jj%2==1) jj+=1; 
     else jj+=1; 
       for(int tab=0;tab<jj;tab++) temppp=temppp+" "; 
     tempti=tempti+temppp; 
     if(ti==3){ 
       System.out.println(tempti); 
       tempti=""; 
       ti=0; 
       } 
     beg+=u0; 
     } 
     System.out.println(tempti); 
  } 
}

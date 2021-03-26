package gradle.IFace;

import java.util.*;

 class Sheet {
     public HashSet<Integer> Num;
     public int Bonus;
     Random r = new Random();

     public Sheet() {
         this.Bonus = r.nextInt(10);
         this.Num = new HashSet<Integer>();
         while (Num.size() != 6) Num.add(r.nextInt(45) + 1);
     }
 }
 class Buyer{
     public String phone="010-";
     public Sheet L=null;

     public Buyer(){}
     public Buyer(Sheet _L){
         Random r = new Random();
         L=_L;
         while(phone.length()!=13) {
             if(phone.length()==8) phone=phone+"-";
             phone = phone + r.nextInt(10);
         }
     }

 }

 public class Lotto{
     public static HashSet LottoSet(int n){
         HashSet<Sheet> set=new HashSet<Sheet>();
         for(int i=0;i<n;i++){
             Sheet s=new Sheet();
             set.add(s);
         }
         return set;
     }
     public static HashSet Purchase(HashSet Set,int cnt){
         HashSet<Buyer> res=new HashSet<Buyer>();
         Iterator it=(Iterator) Set.iterator();
         while(res.size()!=cnt){
             Buyer a=new Buyer((Sheet) it.next());
             res.add(a);
         }
         return res;
     }
     public static Sheet Win(HashSet Set){
         Sheet winner=new Sheet();
         ArrayList<Sheet>L=new ArrayList<Sheet>(Set);
         Random rand=new Random();
         int a=rand.nextInt(Set.size());
         winner=L.get(a);
         return winner;
     }
     public static String Rank(Sheet winner,Sheet buyer){
         String rank="낙첨";
         buyer.Num.retainAll(winner.Num);
         if(buyer.Num.size()==6) rank="1등";
         else if(buyer.Num.size()==5&&buyer.Bonus==winner.Bonus) rank="2등--------------------------------";
         else if(buyer.Num.size()==5&&buyer.Bonus!=winner.Bonus) rank="3등";
         else if(buyer.Num.size()==4) rank="4등";
         else if(buyer.Num.size()==3) rank="5등";
         return rank;
     }

     public static void main(String[] args) {
         HashSet<Sheet>set=new HashSet<Sheet>();
         set=LottoSet(1000);
         int a=30;
         Sheet winner=new Sheet();
         winner=Win(set);
         ArrayList<Integer>L=new ArrayList<Integer>(winner.Num);
         System.out.println("당첨복권은 보너스 번호: "+winner.Bonus+" - "+L);
         Iterator it=(Iterator) Purchase(set,a).iterator();
         System.out.println("총 구매자: "+a);
         while(it.hasNext()){
             Buyer b=(Buyer)it.next();
             ArrayList<Integer>List=new ArrayList<Integer>(b.L.Num);
             System.out.println(b.phone+"/ 보너스 번호: "+b.L.Bonus+" - "+List+"-"+Rank(winner,b.L));
         }

     }
 }
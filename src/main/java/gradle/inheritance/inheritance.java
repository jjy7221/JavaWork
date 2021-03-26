 package gradle.inheritance;

 // 배열 전체 출력 - import(Arrays) - toString();
 //System.out.print(Arrays.toString(BCmate));

 import java.util.Stack;
 import java.util.Arrays;
 import java.util.Random;
 import java.util.Scanner;

 class Person{
   private String name;
   private int age;
   private String sex;

   Person(){
     this.name = "NULL";
     this.age = 0;
     this.sex = "Male";
   }

   Person(String name, int age, String sex){
     this.name = name;
     this.age = age;
     this.sex = sex;
   }

   void Show(){
     System.out.printf("%15s",this.name);
     System.out.printf("%4d 세",this.age);
     System.out.printf("%8s",this.sex);
   }
 }

 class Student extends Person{
   private double grade;
   private int S_id;

   Student(){
     super();
     this.grade = 0.0;
     this.S_id = 00000000;
   }

   Student(String name, int age, String sex, double grade, int S_id){
     super(name, age, sex);
     this.grade = grade;
     this.S_id = S_id;
   }

   @Override
   void Show(){
     super.Show();
     System.out.printf("%5.1f 점",this.grade);
     System.out.printf("%10d",this.S_id);
   }
 }

 class Workstudent extends Student{
   private String Job;
   private int salary;

   Workstudent(){
     super();
     this.Job = "NULL";
     this.salary = 0;
   }

   Workstudent(String name, int age, String sex, double grade, int S_id, String Job, int salary){
     super(name, age, sex, grade, S_id);
     this.Job = Job;
     this.salary = salary;
   }

   @Override
   void Show(){
     super.Show();
     System.out.printf("%6d 만(원)",this.salary);
     System.out.printf("%10s",this.Job);
   }
 }

 public class inheritance {

   static Person[] InitObj(Scanner scanner){
     String[] BCmate = {"Jin02", "Master Kim", "Quiet Woon", "Lee pearl", "Zunaxi", "Lim","YG", "Horse cheong", "Yeo aftershock", "Mark Jo"};
     String[] jobs = {"군인","교사","아이돌","가정주부","기러기 아빠","렉카충","배달의 민족","프로그래머","프로게이머","배구선수","노숙자","100만 유투버", "잘생김", "이쁨", "못생김","회장님","성대근 밑에 직원", "  Air Pusan Crew", "  King in the North","3대 500 헬창", "모델","중고차 딜러","카푸어","탈모인","결혼 못함","오목허접","박사"};
     String[] MorF = {"Male", "Female"};

     Person[] ret = new Person[BCmate.length];

     Random random = new Random();
     random.setSeed(System.currentTimeMillis());



     for(int i = 0; i < BCmate.length; i++){
       //BC401[i] = new Workstudent( BCmate[i], scanner.nextInt(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.nextInt());

       ret[i] = new Workstudent( BCmate[i], random.nextInt(6)+25, MorF[random.nextInt(MorF.length)], random.nextInt(5) + random.nextInt(6)*0.1, random.nextInt(210000)+20000000, jobs[random.nextInt(jobs.length)], random.nextInt(5000)+2000);
     }
     return ret;
   }

   static void display(Person[] Array){
     for(Person S  : Array){
       S.Show();
       System.out.println();
     }
   }

   static Person[] doStack(Person[] px) {
     Person[] temp;
     Stack<Person> BCPNU = new Stack<>();

     for (int i = 0; i < px.length; i++) {
       BCPNU.push(px[i]);
     }
     temp = new Person[BCPNU.size()];

     for (int i = 0; i < px.length; i++) {
       temp[i] = BCPNU.pop();
     }
     return temp;
   }

   public static void main(String[] args){
     Scanner scanner = new Scanner(System.in);

     Person[] BC401 = InitObj(scanner);
     display(BC401);

      Person[] p;
      p = doStack(BC401);
      display(p);

      scanner.close();
   }
 }

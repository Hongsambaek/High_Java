package kr.or.ddit.basic;

public class T14SyncThreadTest {
   public static void main(String[] args) {
      ShareObject sObj = new ShareObject();
      WorkThread th1 = new WorkThread("Thread1", sObj);
      WorkThread th2 = new WorkThread("Thread2", sObj);
      th1.start();
      th2.start();
   }
}


// 공유객체용 클래스
class ShareObject{
   private int sum = 0;
   
   //public synchronized void add()
   public void add() {
      
//      Mutex : Mutual Exclusion Object (상호 배제 : 동시접근 차단)
   //   synchronized(this) {
         
         for(int i=0; i<1000000000; i++) {}
         
      
      
      
      
      int n = sum;
      n += 10;
      
      sum = n;
      
      System.out.println(Thread.currentThread().getName()+" 합계 : "+sum);
      //}
   }
}

//작업수행 thread
class WorkThread extends Thread{
   
   private ShareObject sObj;

   public WorkThread(String name, ShareObject sObj) {
      super(name);
      this.sObj = sObj;
   }
   @Override 
   public void run() {
      for(int i=1; i<=10; i++) {
         
         //동기화방법2 : 동기화 블럭으로 설정하기 2
         synchronized (sObj) {
            sObj.add();            
         }
      }
   } 
}
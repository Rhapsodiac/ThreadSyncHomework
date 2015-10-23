/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsynctest;

/**
 *
 * @author Taylor
 */

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
public class ThreadSyncTest{

    /**
     * @param args the command line arguments
     * write a program that launches 1000 threads. Each thread adds 1 to a 
     * variable sum that initially 0. Define an Integer wrapper object to hold sum. 
     * Run the program with and without synchronization to see its effect.
     */
    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newCachedThreadPool();        
        for (int i = 1; i <= 1000; i++){
            executor.execute(new AddSum());
        }      
        
        executor.shutdown();
        
        while(!executor.isTerminated()){}
        
        System.out.println("Sum is " + Sum);             
    }
    
    private static class AddSum implements Runnable{
        
        private static Lock lock = new ReentrantLock();
        
        @Override
        public void run(){
            lock.lock();
            try{
            int newSum = Sum+1;
            Thread.sleep(5);
            Sum = newSum;
            }            
            catch(InterruptedException ex){}            
            finally{
               lock.unlock();            
            }
        }
    }    
    private static Integer Sum = 0;    
}
    
 
    


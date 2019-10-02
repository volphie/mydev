package my.exercise.concurrency;

import java.lang.Integer;
import java.lang.Exception;
import java.lang.InterruptedException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // ExecutorService executor = Executors.newSingleThreadExecutor();
		// executor.submit(() -> {
			// String threadName = Thread.currentThread().getName();
			// System.out.println("Hello " + threadName);
		// });
		
		Callable<Integer> task = () -> {
			try {
					System.out.println("The Task is running on "+ Thread.currentThread().getName());
					TimeUnit.SECONDS.sleep(1);
					return 123;
				}
				catch (InterruptedException e) {
					throw new IllegalStateException("task interrupted", e);
				}
		};
		
		System.out.println("Main is running on "+ Thread.currentThread().getName());
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		try {
			Future<Integer> future = executor.submit(task);

			System.out.println("future done? " + future.isDone());

			Integer result = future.get();

			System.out.println("future done? " + future.isDone());
			System.out.println("result: " + result); 
				

			System.out.println("attempt to shutdown executor");
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			System.err.println("tasks interrupted");
		}
		catch(Exception e){
			System.err.println("Unknown Exception occurred");
		}
		finally {
			if (!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}
    }
}

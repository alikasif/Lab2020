package java11;

import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFuturePractise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //testSimple();
        //testVoidFutures();
        //testReturnableFuture();
        //testReturnableFutureWithExecutor();
        //testChainWithRunnable();
        //testMultiChain();
        //testAcceptChain();
        //testCompose();
        testCombine();
    }

    static void testCombine() throws ExecutionException, InterruptedException {

        CompletableFuture<String> futureone = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "first future";
            }
        });

        CompletableFuture<String> futuretwo = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                threadSleep(3000);
                return " second future";
            }
        });

        CompletableFuture<String> stringCompletableFuture = futureone.thenCombine(futuretwo, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return s + s2;
            }
        });
        System.out.println(stringCompletableFuture.get());
    }

    static void testCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> futureone = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "first future";
            }
        });

        final CompletableFuture<String> futuretwo = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return " second future";
            }
        });

        CompletableFuture<CompletableFuture<String>> completableFutureCompletableFuture = futureone.thenApply(new Function<String, CompletableFuture<String>>() {
            @Override
            public CompletableFuture<String> apply(String s) {
                return futuretwo;
            }
        });
        System.out.println(completableFutureCompletableFuture.get().get());

        CompletableFuture<String> stringCompletableFuture = futureone.thenCompose(new Function<String, CompletableFuture<String>>() {
            @Override
            public CompletableFuture<String> apply(String s) {
                return futuretwo;
            }
        });

        System.out.println(stringCompletableFuture.get());
    }

    static void testAcceptChain() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                printThreadName();
                threadSleep(2000);
                return " kasif";
            }
        }).thenAcceptAsync(new Consumer<String>() {
            @Override
            public void accept(String s) {
                printThreadName();
                threadSleep(2000);
                System.out.println("hello " + s);
            }
        }).thenRunAsync(new Runnable() {
            @Override
            public void run() {
                printThreadName();
                threadSleep(2000);
                System.out.println("running runnable");
            }
        }).thenRunAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("async runnable");
                printThreadName();
                threadSleep(2000);
            }
        });

        System.out.println(voidCompletableFuture.get());
        System.out.println("done");
    }

    static void testMultiChain() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return " kasif";
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "hello " + s;
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "final chain of "+s;
            }
        });

        System.out.println(stringCompletableFuture.get());
    }

    static void testChainWithRunnable() throws ExecutionException, InterruptedException {

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("running run async..");
            }
        }).thenApply(new Function<Void, String>() {
            @Override
            public String apply(Void unused) {
                return unused+" is null";
            }
        });
        System.out.println(stringCompletableFuture.get());
    }

    static void testReturnableFutureWithExecutor() throws ExecutionException, InterruptedException {
        ExecutorService executor = (ExecutorService) getExecutor();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                printThreadName();
                return "supplier with executor ";
            }
        }, executor);

        System.out.println(stringCompletableFuture.get());
        executor.shutdown();
    }

    static void testReturnableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                threadSleep(3000);
                printThreadName();
                return "suppliable future";
            }
        });
        System.out.println(stringCompletableFuture.get());
    }

    static void testVoidFutures() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("will print...");
                threadSleep(3000);
            }
        });
        voidCompletableFuture.get();
        System.out.println("prinitng after future is completed");
    }

    static void testSimple() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> stringCompletableFuture = new CompletableFuture<String>();
        //stringCompletableFuture.get();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                stringCompletableFuture.complete("hello world");
            }
        }).start();
        System.out.println(stringCompletableFuture.get());
    }

    static void threadSleep(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static Executor getExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    static void printThreadName() {
        System.out.println(Thread.currentThread().getName());
    }
}

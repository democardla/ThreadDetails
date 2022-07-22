# 线程

##创建线程的方法：
## 同步监视器：
我们将由synchronized引导的方法叫做同步方法，该同步方法被声明在哪个对象的类中，就会在调用时把该类的对象当作同步监视器，前提是同步监视器器必须是Object或者其之类的实例：

    synchronized [public] void foo(){
        
    }
我们也可以使用synchronized关键字来指定同步监视器，格式如下：

    synchronized(Object locker){
        <code>//操作共享数据的代码
    }
## 线程操作共享数据
  线程仅仅是工作流程，它除了自身的一些参数之外，任何的关于共享数据的参数和操作方法他都不会保留。因此我们在写代码时，应当将操作共享数据类的方法和类的属性写在被操作的共享数据类的代码块内部。
## 线程通信
  线程通信需要三类方法，分别是：它们只能在 `synchronized` 声明的方法或者代码块中使用。

1。`wait()`  or  `await()`  : 冻结当前线程，并释放同步锁，括号中可以填写自激活时间，当时间结束之后，被冻结的线程会自行解冻，重新就绪；而未填写自激活时间的线程则必须使用下文的两个激活方法来解冻。

2。`notify()`  : 解冻当前线程
 
3。`notifyAll()`  : 解冻所有线程
## 通过继承`Thread`类来创建线程

## 通过`Runnable`接口来创建线程

## 通过`Callable`接口来创建线程
### Overview：
#### 与Runnable接口相比，Callable功能更加强大。
1。相比`run()`方法，`call()`方法可以有返回值

2。`call()`方法可以抛出异常，便于查找线程问题，获取异常信息

3。支持`泛型`的返回值

4。需要借助`FutureTask`类，比如取得返回结果

### 创建步骤：
#### 1。创建实现Callable接口的类实例
#### 2。 创建FutureTask类实例
#### 3。 通过Thread构造器启动线程
    new  Thread(futureTask).start();
#### 4。 **`get()`方法用来获得`FutureTask`构造器中的`Callable`实现类的重写call()的返回值。**
        try {
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (Exception e ) {
            e.printStackTrace();
        }

## 通过`线程池`来创建线程
### Overview：
**背景：** 经常创建和销毁，使用量特别大的资源，比如：并发情况下的线程，对性能影响很大。

**思路：** 提前创建好多个线程，放入线程池中，使用时取用，不使用时放回。可以避免频繁创建和销毁，实现重复利用。类似生活中的公共交通工具。

**优点：** 

####1。提高了响应速度。
####2。降低了资源消耗。
####3。便于线程管理。
**参数：**

    corePoolSize:       核心池的大小
    maximumPoolSize:    最大线程数
    keepAliveTime:      线程没有任务时最多保持多长时间后中止

**操作参数的方法：** 必须要将`ExecutorService`强转成`ThreadPoolExecutor`才能够使用

    setCorePoolSize()       设置核心池的大小
    setMaximumPoolSize()    设置最大线程数
    setKeepAliveTime()      设置线程没有任务时最多保持多长时间后中止
    
### 相关API：
#### JDK5.0提供：`ExecutorService` 和 `Executors` 两个API
#####`ExecutorService`：真正线程池的接口。常见子类`ThreadPoolExecutor`
    void execute(Runnable command):             执行任务/命令，没有返回值，一般用来执行Runnable
    <T> Future <T> submit(Callable<T> task):    执行任务，又返回值，一般用来执行Callable
    void shutdown():                            关闭连接池
#####`Executors`： 工具类线程池工厂，用来创建返回不同类型的线程池
    Executors.newCachedThreadPool()         创建一个可以根据需要创建新线程的线程池
    Executors.newFixedThreadPool()          创建一个可以重用固定线程数的线程池
    Executors.newSingleThreadExecutor()     创建一个只有一个线程的线程池
    Executors.newScheduledThreadPool()      创建一个线程池，他可以安排在给定延迟后运行命令或者定期执行
#####使用：
在主函数中我们要使用线程池来进行编程：

1。提供指定数量的线程池：

    ExecutorService service = Executors.newFixedThreadPool(nThreads: 10);
2。执行线程要进行的操作，需要提供实现对应接口实现类的对象：

    service.execute(Runnable someRunnable);
    service.submit(Callable someCallable);
3。使用完毕关闭线程连接池

    service.shutdown();
**Tips**: 创建多线程有4种方式


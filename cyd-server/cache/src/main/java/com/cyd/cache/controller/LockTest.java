package com.cyd.cache.controller;//package com.cyd.cache.controller;
//
//
//import com.cyd.cache.utils.LockTemplate;
//import com.cyd.cache.zlock.ZLock;
//
//import java.util.concurrent.TimeUnit;
//
////@RestController
////@RequestMapping("/lock/test/")
////@AllArgsConstructor
//public class LockTest {
//
////    private final ILock lock;
////
////    private int count = 0;
//
//    private LockTemplate lockTemplate;
//
//    //@GetMapping("test")
//    //@AddBloomFilter(key = "014654", value = "jdy:re11212")
//    public void test() {
//
//        System.out.println(111111);
////        lock.trLook("jdyun-test",1, TimeUnit.SECONDS);
////        System.out.println("加锁线程id"+Thread.currentThread().getId());
////        System.out.println(count++);
////        lock.unLock("jdyun-test");
////        System.out.println("解锁线程id"+Thread.currentThread().getId());
//    }
//
//   // @GetMapping("test2")
//    public void test2() throws InterruptedException {
//
//        ZLock zLock = lockTemplate.tryLock("jcgl_lock1", 9,10, TimeUnit.MINUTES);
//        System.out.println("加锁线程id" + Thread.currentThread().getId());
//        lockTemplate.unlock(zLock);
//        System.out.println("解锁线程id" + Thread.currentThread().getId());
//    }
//
//    //@RequestMapping("getLock")
//    public void lockTemplate1() {
//        //lock.trLook("jdyun-test", 1, TimeUnit.SECONDS);
////        System.out.println("加锁线程id"+Thread.currentThread().getId());
////        System.out.println(count++);
////        lock.unLock("jdyun-test");
////        System.out.println("解锁线程id"+Thread.currentThread().getId());
//    }
//}

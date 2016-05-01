package com.chenzhicheng.zookeepertest;

import org.apache.zookeeper.*;

/**
 * Created by Administrator on 2016/4/30.
 */
public class ZKClientTest {

    private ZooKeeper zk;

    private Watcher mw;

    private class MyWatcher implements Watcher{
        private ZooKeeper zk1;
        public MyWatcher(ZooKeeper zk1){
            this.zk1 = zk1;
        }
        public void process(WatchedEvent watchedEvent) {
            System.out.println(watchedEvent);
        }
    }

    public ZKClientTest(){
        try {
            mw = new MyWatcher(zk);
            zk = new ZooKeeper("localhost:2181", 3000, mw);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void zkOp() throws Exception{
        if(zk == null){
            throw new RuntimeException("创建zk实例失败，无法完成zk的各个操作测试");
        }
        String data = new String(zk.getData("/zk",false, null));
        System.out.println(data);

        //zk.create("/createzk/clienttest1", "code test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        data = new String(zk.getData("/zk/clienttest",false,null));
        System.out.println(data);


        zk.close();
    }

    public static void main(String[] args) throws Exception{
        ZKClientTest test = new ZKClientTest();
        test.zkOp();
    }
}

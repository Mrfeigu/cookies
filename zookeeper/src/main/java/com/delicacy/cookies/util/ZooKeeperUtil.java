package com.delicacy.cookies.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * zookeeper的基本操作
 *
 * 参考：
 * Api的demo：https://www.jianshu.com/p/70151fc0ef5d
 *
 * @author linzhenghui
 * @date 2020/11/13
 */
public class ZooKeeperUtil {


    public static CuratorFramework client;

    static {

        // 重试机制
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        client = CuratorFrameworkFactory.builder()
                .connectString("172.16.253.108:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                // 隔离命名
                .namespace("lzh-test")
                .build();

        client.start();
    }

    /**
     * PERSISTENT：持久化
     * PERSISTENT_SEQUENTIAL：持久化并且带序列号
     * EPHEMERAL：临时
     * EPHEMERAL_SEQUENTIAL：临时并且带序列号
     *
     * 临时节点：当创建临时节点的程序停掉之后，这个临时节点就会消失。
     *
     * 节点创建，创建失败会抛出异常
     * @throws Exception
     */
    public static void createNode() throws Exception {
        // 如果没有设置节点属性，节点创建模式默认为持久化节点。（内容默认为空，实际的zk存了客户端的ip）
        String path = client.create().forPath("/path");
        // 带有初始值
        String path1 = client.create().forPath("/pathInit", "init".getBytes());
        // 创建临时节点
        String pathTemp = client.create().withMode(CreateMode.EPHEMERAL).forPath("/pathTemp");
        // 创建临时节点带初始值
        String pathTempInit = client.create().withMode(CreateMode.EPHEMERAL).forPath("/pathTempInit", "init".getBytes());
        // 创建一个节点，指定创建模式（临时节点），附带初始化内容，并且自动递归创建父节点
        String pathRecursion = client.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("/meiyoudsjo/pathRecursion", "init".getBytes());
    }

    /**
     * 节点删除，删除失败，会抛出异常
     * @throws Exception
     */
    public static void deleteNode() throws Exception {
        // 删除节点
        client.delete().forPath("/path");
        client.delete().forPath("/pathInit");
        // 删除一个节点，并且递归删除其所有的子节点
        client.delete().deletingChildrenIfNeeded().forPath("/pathRecursion");
        // 删除一个节点，强制指定版本进行删除
        // client.delete().withVersion(10086).forPath("path");
        // 删除一个节点，强制保证删除， client.delete().guaranteed().forPath("path");
        // guaranteed() 接口是一个保障措施，只要客户端会话有效，那么Curator会在后台持续进行删除操作，直到删除节点成功。
        client.delete().guaranteed().forPath("/path");
    }

    /**
     * 读取一个节点
     */
    public static void readNode() throws Exception {

        // 读取存在，没有默认
        byte[] bytes = client.getData().forPath("/path");
        String dataStr = new String(bytes);

        // 读取不存在, 会抛出异常
        // byte[] non = client.getData().forPath("/path1123");

        // 读取有默认
        String dataStr1 = new String(client.getData().forPath("/pathInit"));

        // 读取，并且获得节点状态
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/path");
    }

    /**
     * 修改一个节点
     */
    public static void modifyNode() throws Exception {

        // 检测节点是否存在，不存在stat为null
        Stat stat = client.checkExists().forPath("/pathdcfd");

        // 更新
        Stat stat1 = client.setData().forPath("/path", "test".getBytes());
        // Stat stat2 = client.setData().withVersion(0).forPath("/pathInit", "data".getBytes());

        // 查找路径
        List<String> strings = client.getChildren().forPath("/mojo");

    }

    /**
     * zk的事务
     * @throws Exception
     */
    public static void transaction() throws Exception {
        client.inTransaction()
                .create().withMode(CreateMode.PERSISTENT).forPath("/path123","taylor".getBytes())
                .and()
                .setData().forPath("/path123","alison".getBytes())
                .and()
                .commit();
    }

    /**
     * zk的异步
     * @throws Exception
     */
    public static void asynEvent() throws Exception {
        Executor executor = Executors.newFixedThreadPool(1);
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .inBackground((curatorFramework, curatorEvent) ->
                        System.out.println(String.format("eventType:%s,resultCode:%s",curatorEvent.getType(),curatorEvent.getResultCode())),executor)
                .forPath("/path321");
    }


    public static void main(String[] args) throws Exception {
        // createNode();
        // deleteNode();
        // readNode();
        // modifyNode();
        // transaction();
        // asynEvent();

    }

}

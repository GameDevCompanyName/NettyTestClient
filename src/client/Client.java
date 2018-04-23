package client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {
        ChannelFactory factory = new NioClientSocketChannelFactory(
                Executors.newFixedThreadPool(1),
                Executors.newFixedThreadPool(4)
        );
        ClientBootstrap bootstrap = new ClientBootstrap(factory);
        bootstrap.setPipelineFactory(() -> Channels.pipeline(new ClientHandler()));

        ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 16261));
        try {
            Channel channel = future.await().getChannel();
            Scanner scanner = new Scanner(System.in);
            channel.write(ClientMessage.versionSend("DC0.1"));
            Thread.sleep(10000l);
            channel.write(ClientMessage.loginAttemptSend(String.valueOf(new Random().nextInt(2000)), "440512121"));
            Thread.sleep(10000l);
            channel.write(ClientMessage.messageSend("Алло приём"));
            Thread.sleep(10000l);
            channel.write(ClientMessage.messageSend("Здарова ебало"));
            Thread.sleep(10000l);
            channel.write(ClientMessage.messageSend("ААААААААААААА"));
            Thread.sleep(10000l);
            channel.write(ClientMessage.disconnectSend("Иди нахуй"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

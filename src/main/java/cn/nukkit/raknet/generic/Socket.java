package cn.nukkit.raknet.generic;

import cn.nukkit.utils.MainLogger;
import cn.nukkit.raknet.utils.InternetAddress;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by @ddosnikgit on 3-11-2022.
 * @link https://github.com/RootiTeam/Rooti
 */
public class Socket extends ChannelInboundHandlerAdapter{

	protected final MainLogger logger;
	protected InternetAddress bindAddress;
    protected Bootstrap bootstrap;
    protected EventLoopGroup group;
    protected Channel channel;

    protected ConcurrentLinkedQueue<DatagramPacket> packets = new ConcurrentLinkedQueue<>();

    public Socket(MainLogger logger, InternetAddress bindAddress) {
    	this.bindAddress = bindAddress;
        this.logger = logger;
        try {
            bootstrap = new Bootstrap();
            group = new NioEventLoopGroup();
            bootstrap
                    .group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(this);
            channel = bootstrap.bind(bindAddress.getIp(), bindAddress.getPort()).sync().channel();
        } catch (Exception e) {
            this.logger.critical("Failed to bind socket: Something else is already running on" + bindAddress.toString());
            System.exit(1);
        }
    }

    public InternetAddress getBindAddress() {
    	return this.bindAddress;
    }

    public Channel getSocket() {
    	return this.channel;
    }

    public void close() {
        this.group.shutdownGracefully();
        try {
            this.channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearPacketQueue() {
        this.packets.clear();
    }

    public DatagramPacket readPacket() throws IOException {
        return this.packets.poll();
    }

    public int writePacket(byte[] data, String dest, int port) throws IOException {
        return this.writePacket(data, new InetSocketAddress(dest, port));
    }

    public int writePacket(byte[] data, InetSocketAddress dest) throws IOException {
        channel.writeAndFlush(new DatagramPacket(Unpooled.wrappedBuffer(data), dest));
        return data.length;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.packets.add((DatagramPacket) msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        this.logger.warning(cause.getMessage(), cause);
    }
}
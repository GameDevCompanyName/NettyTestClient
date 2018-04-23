package client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

import java.io.UnsupportedEncodingException;

public class ClientHandler extends SimpleChannelHandler {

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

        //TODO сделать так чтобы всё работало с пакетами типа чтобы сообщение не разрывалось на несколько частей как оно скорее всего и будет

        String message = getStringFromBuffer((ChannelBuffer) e.getMessage());
        System.out.println(message);

    }

    private String getStringFromBuffer(ChannelBuffer buffer) throws UnsupportedEncodingException {
        int bufSize = buffer.readableBytes();
        byte[] byteBuffer = new byte[bufSize];
        buffer.readBytes(byteBuffer);
        return new String(byteBuffer, "UTF-8");
    }

    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        String message = (String) e.getMessage();
        Channels.write(
                ctx,
                e.getFuture(),
                ChannelBuffers.wrappedBuffer(message.getBytes("UTF-8")),
                e.getRemoteAddress()
        );
    }

}

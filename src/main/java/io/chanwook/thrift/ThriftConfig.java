package io.chanwook.thrift;

import com.facebook.nifty.processor.NiftyProcessorAdapters;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServiceProcessor;
import io.chanwook.thrift.sample.ThriftSampleService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import java.util.Arrays;

/**
 * @author chanwook
 */
@Configuration
public class ThriftConfig {

    @Bean
    public TProtocolFactory tProtocolFactory() {
        return new TBinaryProtocol.Factory();
    }

    @Bean
    public ThriftCodecManager thriftCodecManager() {
        return new ThriftCodecManager();
    }

    @Bean
    public Servlet thriftServlet(ThriftCodecManager codecManager, TProtocolFactory protocolFactory, ThriftSampleService service) {
        final ThriftServiceProcessor serviceProcessor = new ThriftServiceProcessor(codecManager, Arrays.<ThriftEventHandler>asList(), service);
        return new TServlet(NiftyProcessorAdapters.processorToTProcessor(serviceProcessor),
                protocolFactory,
                protocolFactory
        );
    }
}

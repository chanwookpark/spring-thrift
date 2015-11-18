package io.chanwook.thrift.sample;

import com.facebook.nifty.client.HttpClientConnector;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftClientManager;
import io.chanwook.thrift.App;
import org.apache.thrift.protocol.TProtocolFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.URI;

/**
 * @author chanwook
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest("server.port:1111")
public class SampleThriftTest {

    @Autowired
    TProtocolFactory protocolFactory;

    @Autowired
    ThriftCodecManager codecManager;

    @Value("${local.server.port}")
    int port;

    ThriftSampleService client;

    @Before
    public void setUp() throws Exception {
        HttpClientConnector connector = new HttpClientConnector(URI.create("http://localhost:" + port + "/thrift/"));
        final ThriftClientManager clientManager = new ThriftClientManager(codecManager);
        client = clientManager.createClient(connector, ThriftSampleService.class).get();
    }

    @Test
    public void config() throws Exception {
        assert port == 1111;
        assert client != null;
    }

    @Test
    public void case1() throws Exception {
        assert 3 == client.method1(1, 2, "ok", TOperation.TYPE2);
    }

    @Test(expected = ThriftSampleException.class)
    public void case2() throws Exception {
        client.method1(0, 0, "exception", TOperation.TYPE1);
    }
}

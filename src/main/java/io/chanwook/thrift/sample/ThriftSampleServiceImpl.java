package io.chanwook.thrift.sample;

import org.springframework.stereotype.Component;

/**
 * @author chanwook
 */
@Component
public class ThriftSampleServiceImpl implements ThriftSampleService {
    @Override
    public int method1(int num1, int num2, String text1, TOperation op) throws ThriftSampleException {
        if (num1 + num2 == 0) {
            throw new ThriftSampleException();
        }
        System.out.println("Thrift Cal :: " + num1 + "-" + num2 + "-" + text1 + "-" + op);
        return num1 + num2;
    }
}

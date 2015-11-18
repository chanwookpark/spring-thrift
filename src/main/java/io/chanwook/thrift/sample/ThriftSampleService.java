package io.chanwook.thrift.sample;

import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;

/**
 * @author chanwook
 */
@ThriftService
public interface ThriftSampleService {

    @ThriftMethod
    int method1(int num1, int num2, String text1, TOperation op) throws ThriftSampleException;
}

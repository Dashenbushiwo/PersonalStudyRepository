package main.java.convert;

import main.java.entity.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * String to Address转换器
 */
@Component
public class AddressConvert implements Converter<String, Address> {
    @Override
    public Address convert(String s) {
        Address address = new Address();
        address.setCity(s);
        return address;
    }
}

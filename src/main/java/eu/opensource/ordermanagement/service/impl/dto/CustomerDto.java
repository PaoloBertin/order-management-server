package eu.opensource.ordermanagement.service.impl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private Long customerId;

    private String firstname;

    private String lastname;

    private String username;

    private Long header_addres_id;

    private Long delivery_addres_id;
}

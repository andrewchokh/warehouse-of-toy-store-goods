package com.andrewchokh.wtsg.service.contract;

import com.andrewchokh.wtsg.dto.UserAddDTO;
import java.util.function.Supplier;

public interface SignUpService {

    void signUp(UserAddDTO userAddDTO, Supplier<String> userInput);
}

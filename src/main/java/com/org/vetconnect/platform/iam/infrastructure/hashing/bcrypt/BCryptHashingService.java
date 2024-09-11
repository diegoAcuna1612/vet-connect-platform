package com.org.vetconnect.platform.iam.infrastructure.hashing.bcrypt;

import com.org.vetconnect.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}

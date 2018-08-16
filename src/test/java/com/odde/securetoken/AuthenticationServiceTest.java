package com.odde.securetoken;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @Test
    public void is_valid_test() {
        ProfileDao stubProfileDao = mock(ProfileDao.class);
        RsaTokenDao stubRsaTokenDao = mock(RsaTokenDao.class);
        OtpLogger mockOtpLogger = mock(OtpLogger.class);
        AuthenticationService target = new AuthenticationService(stubProfileDao, stubRsaTokenDao, mockOtpLogger);

        when(stubProfileDao.getPassword("joey")).thenReturn("91");
        when(stubRsaTokenDao.getRandom("joey")).thenReturn("000000");

        boolean actual = target.isValid("joey", "91000000");

        assertTrue(actual);
        verify(mockOtpLogger).log("invalid");
    }

}

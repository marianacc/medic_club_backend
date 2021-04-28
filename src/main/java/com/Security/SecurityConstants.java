package com.Security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_500_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String PATIENT_CREATION = "/patients/sign-up";
    public static final String DOCTOR_CREATION = "/doctors/sign-up";
    public static final String MULTIPLE_PATIENTS_CREATION = "/patients/loadPatientsFromJson";
    public static final String SWAGGER_API_DOCUMENTATION = "/v2/api-docs";
    public static final String SWAGGER_API_UI = "/swagger-ui.html";
    public static final String LOGIN_WITH_GOOGLE = "/app-user/continue_with_google";
}
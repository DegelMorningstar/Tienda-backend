package urbandeport.tienda.auth;

public class JwtConfig {
    public static final String CLIENT_NAME = "urbandeport.tienda";
    public static final String SECRET_KEY = "QbR4mZjrAL7DExQEJVTXel6bd54DMnwMJOATYQzEwfFmiMPcmHJfrqkAEKb7z/q3";
    public static final String RSA_PRIVATE ="-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAsMjQ2V2eeifS+Ggo+m0Iypl+YqN4NzPTYvKzCF+jTXrBe7vd\n" +
            "qvMo9JFZhLlZFr9ZOdZko2ymTQz+SLn0kSEI35z7bKOXQRfO6BLxuGphcgEDsDg7\n" +
            "iDIzMDqmuDhjrhLKt3f33rrPNKWyBReRsheLVLEaBWjjc5QdFi0hq+Wm/aKuPPFY\n" +
            "b6Y/kds1cAjWDJgzXrPQLRfgiVtu0KVRhx7U/CYQn9+z1zrxMPq7voDPpx7Q0nFR\n" +
            "8QH9mB1eKhvhOwFR96IiBX5Sve45nOC0pLCrkPTfSbV0Jgr3Moe7/Tw4I2ZtQC9Z\n" +
            "1IEA18UJbQygFbHungvhF0j1ILErLW3k7wUchwIDAQABAoIBAHOglUFmw03gmFOT\n" +
            "RSIuiK6C4LBBfVKV2jAAz+qtKxF1NfHpOZ4jdukn3GY8GLIRK5nxyVc+rDL/SZ5l\n" +
            "yjnWYohnk+dFxJt1qAYQxfs50UyQr0nmLcHyG5ry8bLSZxgnAGIp3HzYsMgdjCda\n" +
            "eBgE4EPfpkZdiU9CVBf6Iujcx3FijLMcxwMcjJNWmSNJhm9KCDt9GjBSqE5S4sw2\n" +
            "K/pMvXTopkk3L9GD72GdDW+5hdRmCvx7MnHzFYiIR6VI8+KNW3IH1s+y/1+gi9tM\n" +
            "bE8RpX6KL6PO53qGRdjhhXnyJh8EZZox1vdLDkERkzXUrBoqDwd7KXu+DVNM4Trl\n" +
            "hG9oIvkCgYEA2ni1RSFocUAvv8DbW5qZA8mFUPPg11yDCYzfjlysk53xTxW2nOr+\n" +
            "Mi+eAUvvIwpEE9VYVh+LR5c5sUwKxY5oRlyfk3l87soZsK7qENeOyoEZaBsTzm1T\n" +
            "kMYOnFU2KtITPGkKVPYYpV74UzZjE/z3oTnnYo90AgtOkf40VJcjpfMCgYEAzybq\n" +
            "xktV5+GBDdDYXRY+Fp2cyEFl2rwzppCKMznf0Uh6exFty0GeLYY30c/wc0wwLL54\n" +
            "uyTbXTp7E5AMQ3ttTHe5a/bkSAQ+DjNy4fagsj6UfAODvJYxP9ws+blPPcyf7KPP\n" +
            "ge7A54g1hFS6LKMmvTTIFH57adDE1SKYV15bcB0CgYBpRvOrHX8z28dmsuvBx8zm\n" +
            "m0yoBu2ZOhZXGtB7zMLI27p17rOyCDd4PaP5YfsZuo+1swA/eDHozMHbYxZpvscW\n" +
            "6Plo2olOlIsurgtTFFzVzsJMfYfc2KgFQru1sAZLOjJU0RVOfczAm/Q0PSgfcK5v\n" +
            "vfR3dUNk3Mf+6C2VecOO6wKBgQDA399g2ewWhuR+VKHfqkINFJtR6XJIg0skHxhO\n" +
            "MeivF+jShZvQ0DvS71ExzYZ4E8wXHRGUTfDhkP3fbRO+EUHnHOJMIAjU9P5CgqzI\n" +
            "r7VXwOTHww35nW4lo39M1LlvNQMCdlNTC1YZPb12hN0DirUbHfXc/xcxouxh9mAZ\n" +
            "mQVSEQKBgQCVU/chgmG4ReiyLrhVbC8wQSpOH1h9eZ2JbE2nfIp2zuBuWH54RcCJ\n" +
            "V84MpT5Uvjslvz1bIUd3fnmzXB6tqMqZbR9ZHWPcW7VOM8bXq59f+U54gTEiq0QK\n" +
            "e2pLlkR2ruXlAGE0Z6zWjyTI/Uw6JGPNk5LcgEQyTWu6CL9rxM+WSg==\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsMjQ2V2eeifS+Ggo+m0I\n" +
            "ypl+YqN4NzPTYvKzCF+jTXrBe7vdqvMo9JFZhLlZFr9ZOdZko2ymTQz+SLn0kSEI\n" +
            "35z7bKOXQRfO6BLxuGphcgEDsDg7iDIzMDqmuDhjrhLKt3f33rrPNKWyBReRsheL\n" +
            "VLEaBWjjc5QdFi0hq+Wm/aKuPPFYb6Y/kds1cAjWDJgzXrPQLRfgiVtu0KVRhx7U\n" +
            "/CYQn9+z1zrxMPq7voDPpx7Q0nFR8QH9mB1eKhvhOwFR96IiBX5Sve45nOC0pLCr\n" +
            "kPTfSbV0Jgr3Moe7/Tw4I2ZtQC9Z1IEA18UJbQygFbHungvhF0j1ILErLW3k7wUc\n" +
            "hwIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}

package com.reputation.dependencies;


import com.google.common.net.InternetDomainName;
import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GuavaDomainTest {

    private String[] validPrivateDomains = new String[]{"leaf.nissan", "kia.kia", "action.tickets"};
    private String[] noneExistingPrivateDomains = new String[]{"leaf.nissanNoExist", "kia.kiaNoExist", "action.ticketsNoExist"};

    private String[] internationalDomains = new String[]{"jamö.at", "müller.com"};

    @Test
    public void testValidPrivateDomains() {
        testValidDomains(validPrivateDomains);
        testValidDomains(noneExistingPrivateDomains);

    }

    @Test
    public void testValidInternationalDomains() {
        testValidDomains(internationalDomains);
    }


    private void testValidDomains(String... domains) {
        for (String domain : domains) {
            InternetDomainName dname = InternetDomainName.from(domain);
            List<String> dparts = dname.parts();
            StringBuilder sb = new StringBuilder();
            for (String part : dparts) {
                sb.append(part).append('.');
            }
            String builtDomain = sb.substring(0, sb.length() - 1);
            assertEquals("Domain is not the same: " + domain + " vs " + builtDomain, domain.toLowerCase(), builtDomain);
        }

    }

}

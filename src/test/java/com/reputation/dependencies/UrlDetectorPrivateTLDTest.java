package com.reputation.dependencies;


import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UrlDetectorPrivateTLDTest {

    private String[] validPrivateDomains = new String[]{"http://leaf.nissan/", "http://kia.kia/", "ftp://action.tickets/"};
    private String[] noneExistingPrivateDomains = new String[]{"http://leaf.nissanNoExist/", "http://kia.kiaNoExist/", "ftp://action.ticketsNoExist/"};

    private String[] internationalDomains = new String[]{"http://jamö.at/", "http://müller.com/", "https://schadlinge.de/red-schädlinge/"};

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
            UrlDetector parser = new UrlDetector(new StringBuilder().append("before the domain ").append(domain).append(" and now after domain name").toString(), UrlDetectorOptions.Default);
            List<Url> found = parser.detect();
            assertEquals("Should find exactly one domain: " + domain, 1, found.size());
            Url url = found.get(0);
            String parsedUrl = new StringBuilder().append(url.getScheme()).append("://").append(url.getHost()).append(url.getPath()).toString();
            assertEquals(domain, parsedUrl);
        }

    }

}

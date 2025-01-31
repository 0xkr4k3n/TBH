package com.tbh.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

@Service
public class DnsService {
        // Configuration path for dnsmasq additional hosts
        @Value("${dnsmasq.config.path:/usr/local/etc/dnsmasq.conf}")
        private String DNSMASQ_CONFIG_PATH;

        // IP address to map challenges (typically localhost)
        @Value("${dnsmasq.default.ip:127.0.0.1}")
        private String DEFAULT_IP;

        /**
         * Add a new challenge domain to dnsmasq configuration
         *
         * @param challengeDomain Domain to be added (e.g., "mywebchallenge")
         * @throws IOException If file writing fails
         */
        public void addChallengeDomain(String challengeDomain) throws IOException {
            // Validate domain format
            validateDomainName(challengeDomain);

            // Construct full domain entry
            String domainEntry = String.format("address=/%s.tbh.com/%s",
                    challengeDomain,
                    DEFAULT_IP
            );

            // Append to dnsmasq configuration
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DNSMASQ_CONFIG_PATH, true))) {
                writer.write(domainEntry);
                writer.newLine();
            }

            // Reload dnsmasq to apply changes
            reloadDnsmasqService();
        }

        /**
         * Remove a challenge domain from dnsmasq configuration
         *
         * @param challengeDomain Domain to be removed
         * @throws IOException If file reading/writing fails
         */
        public void removeDomain(String challengeDomain) throws IOException {
            List<String> lines = Files.readAllLines(Paths.get(DNSMASQ_CONFIG_PATH));

            // Filter out lines matching the specific domain
            List<String> updatedLines = lines.stream()
                    .filter(line -> !line.contains(String.format("%s.tbh.com", challengeDomain)))
                    .collect(Collectors.toList());

            // Write back filtered lines
            Files.write(Paths.get(DNSMASQ_CONFIG_PATH), updatedLines);

            // Reload dnsmasq
            reloadDnsmasqService();
        }

        /**
         * List all currently configured challenge domains
         *
         * @return List of challenge domains
         * @throws IOException If file reading fails
         */
        public List<String> listChallengeDomains() throws IOException {
            return Files.readAllLines(Paths.get(DNSMASQ_CONFIG_PATH)).stream()
                    .filter(line -> line.startsWith("address="))
                    .map(this::extractDomainFromLine)
                    .collect(Collectors.toList());
        }

        /**
         * Validate domain name to prevent malicious inputs
         *
         * @param domain Domain name to validate
         * @throws IllegalArgumentException If domain is invalid
         */
        private void validateDomainName(String domain) {
            if (!domain.matches("^[a-zA-Z0-9-]+$")) {
                throw new IllegalArgumentException("Invalid domain name: " + domain);
            }
        }

        /**
         * Extract domain from dnsmasq configuration line
         *
         * @param line Configuration line
         * @return Extracted domain name
         */
        private String extractDomainFromLine(String line) {
            // Regex to extract domain from address configuration
            return line.replaceAll("address=/([^/]+)\\.challenge\\.local/.*", "$1");
        }

        /**
         * Reload dnsmasq service to apply configuration changes
         * Requires sudo privileges
         */
        private void reloadDnsmasqService() throws IOException {
            Runtime.getRuntime().exec("brew services restart dnsmasq");
        }
    }
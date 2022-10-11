package com.example.demo.fsm.config;

import com.example.demo.property.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource(value = "classpath:rule.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "rule.config")
public class RulesConfiguration {

    private List<RuleGroupConfig> ruleGroup;

    public List<RuleGroupConfig> getRuleGroup() {
        return ruleGroup;
    }

    public void setRuleGroup(List<RuleGroupConfig> ruleGroup) {
        this.ruleGroup = ruleGroup;
    }
}

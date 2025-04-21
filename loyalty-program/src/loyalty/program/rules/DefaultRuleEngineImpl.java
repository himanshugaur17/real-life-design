package loyalty.program.rules;

import java.util.List;

import loyalty.program.models.CustomerAction;
import loyalty.program.models.LoyaltyPoint;

public class DefaultRuleEngineImpl implements RuleEngine {
    private final List<LoyaltyPointRule> loyaltyPointRules;

    public DefaultRuleEngineImpl(List<LoyaltyPointRule> loyaltyPointRules) {
        this.loyaltyPointRules = loyaltyPointRules;
    }

    @Override
    public List<LoyaltyPoint> apply(CustomerAction context) {
        return loyaltyPointRules.stream().map(rule -> rule.applyRule(context)).flatMap(ls -> ls.stream())
                .toList();
    }

}

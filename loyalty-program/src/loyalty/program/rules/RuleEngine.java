package loyalty.program.rules;

import java.util.List;

import loyalty.program.models.CustomerAction;
import loyalty.program.models.LoyaltyPoint;

public interface RuleEngine {
    List<LoyaltyPoint> apply(CustomerAction context);
}

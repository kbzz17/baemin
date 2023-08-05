package jw.project.baemin.customer.domain.enums;

public enum CouponType {
    FIXED{
        @Override
        public int discount(int currentPrice, int amount) {
            return currentPrice - amount;
        }

        @Override
        public boolean valid(int currentPrice, int amount) {
            return currentPrice >= amount;
        }
    },
    PERCENTAGE{
        @Override
        public int discount(int currentPrice, int percent) {
            return currentPrice * (100 - percent) / 100;
        }

        @Override
        public boolean valid(int currentPrice, int percent) {
            return (percent > 0) && (percent <= 100);
        }
    };

    public int discount(int currentPrice, int amount) {
        return 0;
    }

    public boolean valid(int currentPrice, int amount){
        return true;
    }
}

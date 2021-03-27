package com.epam.properties.applience.device;

/**
 * Display types
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public enum DisplayType {

    LCD {
        public int displayType() {
            return 7;
        }

        public String getName() {
            return "LCD";
        }
    },
    TFT {
        public int displayType() {
            return 6;
        }

        public String getName() {
            return "TFT";
        }
    },
    IPS {
        public int displayType() {
            return 5;
        }

        public String getName() {
            return "IPS";
        }
    },
    OLED {
        public int displayType() {
            return 4;
        }

        public String getName() {
            return "OLED";
        }
    },
    AMOLED {
        public int displayType() {
            return 3;
        }

        public String getName() {
            return "AMOLED";
        }
    },
    TN {
        public int displayType() {
            return 2;
        }

        public String getName() {
            return "TN";
        }
    },
    QLED {
        public int displayType() {
            return 1;
        }

        public String getName() {
            return "QLED";
        }
    },
    ULED {
        public int displayType() {
            return 0;
        }

        public String getName() {
            return "ULED";
        }
    };

    /**
     * Display type coefficient getter
     * @return display type coefficient
     */
    public abstract int displayType();

    /**
     * Display type getter
     * @return display type
     */
    public abstract String getName();
}

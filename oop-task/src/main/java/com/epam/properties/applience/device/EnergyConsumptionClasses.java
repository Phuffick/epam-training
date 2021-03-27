package com.epam.properties.applience.device;

/**
 * Energy consumption classes.
 * Defines class that device belongs according to energy consumption
 *
 * @version    1.0.0 11 March 2021
 * @author     Belyack Maxim
 */
public enum EnergyConsumptionClasses {

    A_PLUS_PLUS_PLUS {
        public String getName() {
            return "A+++";
        }

        public int energyConsumption() {
            return 9;
        }
    },
    A_PLUS_PLUS {
        public String getName() {
            return "A++";
        }

        public int energyConsumption() {
            return 8;
        }
    },
    A_PLUS {
        public String getName() {
            return "A+";
        }

        public int energyConsumption() {
            return 7;
        }
    },
    A {
        public String getName() {
            return "A";
        }

        public int energyConsumption() {
            return 6;
        }
    },
    B {
        public String getName() {
            return "B";
        }

        public int energyConsumption() {
            return 5;
        }
    },
    C {
        public String getName() {
            return "C";
        }

        public int energyConsumption() {
            return 4;
        }
    },
    D {
        public String getName() {
            return "D";
        }

        public int energyConsumption() {
            return 3;
        }
    },
    E {
        public String getName() {
            return "E";
        }

        public int energyConsumption() {
            return 2;
        }
    },
    F {
        public int energyConsumption() {
            return 1;
        }

        public String getName() {
            return "F";
        }
    },
    G {
        public int energyConsumption() {
            return 0;
        }

        public String getName() {
            return "G";
        }
    };

    /**
     * Energy consumption coefficient getter
     * @return energy consumption coefficient
     */
    public abstract int energyConsumption();

    /**
     * energy consumption type getter
     * @return energy consumption type
     */
    public abstract String getName();
}

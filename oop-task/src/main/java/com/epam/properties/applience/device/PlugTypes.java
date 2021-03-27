package com.epam.properties.applience.device;

/**
 * Plug types.
 * Defines types that fork might be belong to.
 * All the types are used (or were used in the past)
 * with devices and other electric appliances
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public enum PlugTypes {

    TYPE_A {
        public String getName() {
            return "Type A";
        }
    },
    TYPE_B {
        public String getName() {
            return "Type B";
        }
    },
    TYPE_C {
        public String getName() {
            return "Type C";
        }
    },
    TYPE_D {
        public String getName() {
            return "Type D";
        }
    },
    TYPE_E {
        public String getName() {
            return "Type E";
        }
    },
    TYPE_F {
        public String getName() {
            return "Type F";
        }
    },
    TYPE_G {
        public String getName() {
            return "Type G";
        }
    },
    TYPE_H {
        public String getName() {
            return "Type H";
        }
    },
    TYPE_I {
        public String getName() {
            return "Type I";
        }
    },
    TYPE_J {
        public String getName() {
            return "Type J";
        }
    },
    TYPE_K {
        public String getName() {
            return "Type K";
        }
    },
    TYPE_L {
        public String getName() {
            return "Type L";
        }
    };

    /**
     * Plug type getter
     * @return plug type
     */
    public abstract String getName();
}

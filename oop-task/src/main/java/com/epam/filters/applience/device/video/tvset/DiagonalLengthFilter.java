package com.epam.filters.applience.device.video.tvset;

import com.epam.entities.applience.devices.video.TvSet;
import com.epam.filters.Filter;

/**
 * Diagonal length TV set filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class DiagonalLengthFilter implements Filter<TvSet> {
    /**
     * Diagonal length to check
     */
    private final int diagonalLength;

    /**
     * Diagonal length to check constructor
     * @param diagonalLength set diagonal length
     */
    public DiagonalLengthFilter(int diagonalLength) {
        this.diagonalLength = diagonalLength;
    };

    /**
     * Compares current diagonal length
     * @param tvSet current TV set
     * @return compare result
     */
    @Override
    public boolean check(TvSet tvSet) {
        return tvSet.getDiagonalLength() == diagonalLength;
    }
}

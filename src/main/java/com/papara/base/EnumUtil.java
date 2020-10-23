package com.papara.base;

import com.papara.api.common.enumeration.EntryType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enum utilities
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @since 0.0.1
 */
public class EnumUtil {

    /**
     * Extract entry types values and match with param.
     *
     * @param entryTypeValue the entry type value
     * @throws PaparaRESTException the papara rest exception
     */
    public static void extractEntryTypesValuesAndMatchWithParam(Integer entryTypeValue) throws PaparaRESTException {
        List<EntryType> entryTypes = Arrays.asList(EntryType.values());
        List<Integer> entryTypesValues = new ArrayList<Integer>();

        for (EntryType et : entryTypes) {
            entryTypesValues.add(et.getValue());
        }

        if (!entryTypesValues.contains(entryTypeValue)) {
            throw new PaparaRESTException("Invalid entryType.");
        }
    }

}

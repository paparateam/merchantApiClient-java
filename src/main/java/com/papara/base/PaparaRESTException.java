package com.papara.base;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST exception handler
 *
 * @author Burak Serpici <burak.serpici@crosstech.com.tr>
 * @version 0.0.1
 * @see Exception
 * @since 0.0.1
 */
public class PaparaRESTException extends Exception {


    private static final Logger log = LoggerFactory
            .getLogger(PaparaRESTException.class);

    /**
     * Serial Version ID
     */
    private static final long serialVersionUID = 1L;

    private int responsecode;

    private Error details;

    /**
     * Instantiates a new Papara rest exception.
     *
     * @param message the message
     */
    public PaparaRESTException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Papara rest exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public PaparaRESTException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new Papara rest exception.
     *
     * @param throwable the throwable
     */
    public PaparaRESTException(Throwable throwable) {
        super(throwable);
    }

    /**
     * Create from http error exception papara rest exception.
     *
     * @param httpErrorException the http error exception
     * @return the papara rest exception
     */
    protected static PaparaRESTException createFromHttpErrorException(HttpErrorException httpErrorException) {
        PaparaRESTException ppre = new PaparaRESTException(httpErrorException.getMessage(), httpErrorException);
        ppre.setResponsecode(httpErrorException.getResponsecode());
        if (httpErrorException.getResponsecode() >= 400 && httpErrorException.getErrorResponse() != null && isJSONValid(httpErrorException.getErrorResponse())) {
            try {
                Error details = JSONFormatter.fromJSON(httpErrorException.getErrorResponse(), Error.class, null);
                ppre.setDetails(details);
            } catch (Exception e) {
                log.error("Exception thrown while parsing error response: " + httpErrorException.getErrorResponse(), e);
            }
        }
        return ppre;
    }

    private static boolean isJSONValid(String jsonInString) {
        try {
            new Gson().fromJson(jsonInString, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    /**
     * Gets responsecode.
     *
     * @return the responsecode
     */
    public int getResponsecode() {
        return responsecode;
    }

    /**
     * Sets responsecode.
     *
     * @param responsecode the responsecode
     */
    public void setResponsecode(int responsecode) {
        this.responsecode = responsecode;
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public Error getDetails() {
        return details;
    }

    /**
     * Sets details.
     *
     * @param details the details
     */
    public void setDetails(Error details) {
        this.details = details;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "response-code: " + this.responsecode + "\tdetails: " + this.details;
    }

}

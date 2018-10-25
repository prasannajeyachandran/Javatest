/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bskyb.internettv.parental_control_implement;

import com.bskyb.internettv.parental_control_service.ParentalControlService;
import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;

/**
 *
 * @author prasanna
 */
public class Movieaccesspermission implements ParentalControlService {
	
    // Movie Service interface  
    private  MovieService movieService;
    
   
        //Default Constructor
        public Movieaccesspermission() {
		 
	}
        
         // Movie Service Constructor
	public Movieaccesspermission(MovieService movieService) {
		this.movieService = movieService;
	}

	/**
         * canWatchMovie method implements from ParentalControlService
         * Compare customer control level against third party movie control level
	 
	* @param: custParentalControlLevel -customer level preference
	* @param: movieId - Movie ID
	* @return boolean -grant or denied to view the movies
	*/

	@Override	
	public boolean canWatchMovie(String movieId, String custParentalControlLevel) throws TechnicalFailureException, TitleNotFoundException, Exception  {
		try{
			int custLevel = 0;
                        custLevel=getLevelCust(custParentalControlLevel);
			int movieLevel=0;
                        movieLevel = getLevelMovie(movieId);
			boolean canWe = movieLevel <= custLevel;
			return canWe;
		}catch (TitleNotFoundException e){
			throw new TitleNotFoundException();
		}catch (TechnicalFailureException e){
			throw new TechnicalFailureException();
		}catch (Exception e){
                        throw new Exception();
                }
    }
	
	/**
                    *Get ControlLevel ID
                    * @param: controlLevel - parental control level from customer
                    * @return control level id
	*/
	private Integer getLevelCust(String controlLevel) throws Exception {
		try{
			int custLevel = ControlLevels.getLevel(controlLevel);
			return custLevel;
		}catch (Exception e){
			throw new Exception("Not found control level: " + controlLevel+ " ", e);
		}
	}
	
	
	/**
	* Movie Service method  
	* @param: id - MovieID
	* @return ControlLevels - Parental Control Level
	*/
	private Integer getLevelMovie(String movieId) throws TitleNotFoundException, TechnicalFailureException,Exception  {
		String controlLevel = movieService.getParentalControlLevel(movieId);
		return ControlLevels.getLevel(controlLevel);	
}
}
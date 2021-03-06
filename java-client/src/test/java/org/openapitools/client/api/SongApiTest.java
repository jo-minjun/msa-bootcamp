/*
 * msa-bootcamp
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.ProblemDetails;
import org.openapitools.client.model.Song;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for SongApi
 */
@Disabled
public class SongApiTest {

    private final SongApi api = new SongApi();

    /**
     * create song
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void createSongTest() throws ApiException {
        Long singerId = null;
        Long albumId = null;
        Long songId = null;
        Song song = null;
        api.createSong(singerId, albumId, songId, song);
        // TODO: test validations
    }

    /**
     * delete song
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteSongTest() throws ApiException {
        Long singerId = null;
        Long albumId = null;
        Long songId = null;
        api.deleteSong(singerId, albumId, songId);
        // TODO: test validations
    }

    /**
     * get song
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getSongTest() throws ApiException {
        Long singerId = null;
        Long albumId = null;
        Long songId = null;
        api.getSong(singerId, albumId, songId);
        // TODO: test validations
    }

    /**
     * get song list
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getSongsTest() throws ApiException {
        Long singerId = null;
        Long albumId = null;
        Integer page = null;
        Integer size = null;
        api.getSongs(singerId, albumId, page, size);
        // TODO: test validations
    }

    /**
     * update song
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void updateSongTest() throws ApiException {
        Long singerId = null;
        Long albumId = null;
        Long songId = null;
        Song song = null;
        api.updateSong(singerId, albumId, songId, song);
        // TODO: test validations
    }

}

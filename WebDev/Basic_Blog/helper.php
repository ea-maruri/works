<?php
include("data.php");

/**
 * Get All Posts
 */
function posts()
{
    global $posts;

    if (isset($posts) && count($posts) > 0) {
        return $posts;
    } else {
        return [];
    }
}

/**
 * Get all menus
 */
function menus()
{
    global $menus;

    if (isset($menus) && count($menus) > 0) {
        return $menus;
    } else {
        return [];
    }
}
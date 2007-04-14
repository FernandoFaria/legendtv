/*****************************************************************************
 * Preamble
 *****************************************************************************/
#include <vlc/vlc.h>
#include <vlc_interface.h>
#include <vlc_vout.h>

/*****************************************************************************
 * Exported prototypes
 *****************************************************************************/
extern int E_(Activate) (vlc_object_t *);
extern int E_(Activate) (vlc_object_t *);

/*****************************************************************************
 * Module descriptor
 *****************************************************************************/
#define MODULE_NAME		vout_ltv-j3d
#define MODULE_NAME_IS	vout_ltv-j3d

vlc_module_begin();
	set_shortname("LTV-Java3D");
	set_description(_("LegendTV Java3D Video Plugin"));
	
	// What we're aiming for here is to prevent this module from being
	// selected automatically when the user is using VLC outside of
	// LegendTV.
	set_capability("video output", 0);
	
	add_shortcut("ltv-j3d");
	
	set_callbacks(E_(Activate), E_(Deactivate));
vlc_module_end();

/**
 * Function called to create and intialize the Java3D video output thread.
 * 
 * We're basically going to rely on the Java code to have prepared for this,
 * so there's not much we do directly.
 * 
 * If the Java code did not prepare for this, or there is no Java code, then
 * this function will assume LegendTV did not initiate this module and it will
 * return an error VLC_EGENERIC.
 * 
 * @param	this	Pointer to the video output state information.
 * @return			VLC_SUCCESS if activation of the video out was anticipated
 * 					and completed successfully; VLC_EGENERIC otherwise.
 */
int E_(Activate) (vlc_object_t *this)
{
}

/**
 * Function called to deactivate the Java3D video output thread.
 * 
 * @param	this	Pointer to the video output state information.
 * @return			VLC_SUCCESS if deactivation was successful.
 */
int E_(Activate) (vlc_object_t *this)
{
}
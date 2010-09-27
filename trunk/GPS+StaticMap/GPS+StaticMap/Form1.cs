using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Net;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using Microsoft.WindowsMobile.Samples.Location;

namespace GPS_StaticMap
{
    public partial class Form1 : Form
    {
        private Gps gps;
        private GoogleMaps gm;
        
        public Form1()
        {
            InitializeComponent();

            begMes.Text = "GeoPB Mobile \n" + "Selecione Iniciar no menu principal.";
            gps = new Gps();
            gm = GoogleMaps.GetInstance();
        }

        private void menuItem1_Click(object sender, EventArgs e)
        {
            Close();
        }

        void gps_LocationChanged(object sender, LocationChangedEventArgs args)
        {
            GpsPosition position = args.Position;
            ControlUpdater cu = UpdateControl;
            
            if ((position.LatitudeValid) & (position.LongitudeValid))
            {
                statusBar1.Visible = true;
                statusBar1.Text = "Localização identificada.";
                Invoke(cu, begLat, position.Latitude.ToString());
                Invoke(cu, begLng, position.Longitude.ToString());
                gps.LocationChanged -= gps_LocationChanged;
                gm.setLatitude(position.Latitude);
                gm.setLongitude(position.Longitude);
                mapBox.Image = gm.RetrieveStaticMap();
            }
        }

        void gps_DeviceStateChanged(object sender, DeviceStateChangedEventArgs args)
        {
            GpsDeviceState device = args.DeviceState;
            ControlUpdater cu = UpdateControl;
        }
        


        private delegate void ControlUpdater(Control c, string s);

        private void UpdateControl(Control c, string s)
        {
            c.Text = s;
        }

        private void Form1_Closing(object sender, CancelEventArgs e)
        {
            if (gps.Opened)
            {
                gps.DeviceStateChanged -= gps_DeviceStateChanged;
                gps.LocationChanged -= gps_LocationChanged;
                gps.Close();
            }
        }

        private void menuItem3_Click(object sender, EventArgs e)
        {
            begMenu.Enabled = false;
            begMes.Visible = false;
            mapBox.Visible = true;
            mapBox.Image = gm.RetrieveStaticMap();
            zoomInMenu.Enabled = true;
            zoomOutMenu.Enabled = true;
            
        }

        private void menuItem2_Click(object sender, EventArgs e)
        {

        }

        private void menuItem4_Click(object sender, EventArgs e)
        {
            mapBox.Visible = true;
            begLat.Visible = false;
            begLng.Visible = false;
            begMes.Visible = false;

            mapBox.Image = gm.RetrieveStaticMap();
        }

        public void setPictureBox(Bitmap image)
        {
            mapBox.Image = image;
        }

        private void menuItem5_Click(object sender, EventArgs e)
        {
            mapBox.Image = gm.zoomIn();
        }

        private void menuItem6_Click(object sender, EventArgs e)
        {
            mapBox.Image = gm.zoomOut();
        }

        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {
            if ((e.KeyCode == System.Windows.Forms.Keys.Up))
            {
                // Up
                if (mapBox.Visible)
                {
                    mapBox.Image = gm.panUp();
                }
            }
            if ((e.KeyCode == System.Windows.Forms.Keys.Down))
            {
                // Down
                if (mapBox.Visible)
                {
                    mapBox.Image = gm.panDown();
                }
            }
            if ((e.KeyCode == System.Windows.Forms.Keys.Left))
            {
                // Left
                if (mapBox.Visible)
                {
                    mapBox.Image = gm.panLeft();
                }
            }
            if ((e.KeyCode == System.Windows.Forms.Keys.Right))
            {
                // Right
                if (mapBox.Visible)
                {
                    mapBox.Image = gm.panRight();
                }
            }
            if ((e.KeyCode == System.Windows.Forms.Keys.Enter))
            {
                // Enter
            }

        }

        private void menuItemGPS_Click(object sender, EventArgs e)
        {
            statusBar1.Visible = true;
            statusBar1.Text = "Procurando Satélites, aguarde";
            if (!gps.Opened)
            {
                gps.LocationChanged += new LocationChangedEventHandler(gps_LocationChanged);
                gps.Open();
            }
            else
            {
                gps.LocationChanged -= gps_LocationChanged;
                gps.Close();
            }
        }

        
    }
}
using System;

using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Net;
using System.Text;

namespace GPS_StaticMap
{
    class GoogleMaps
    {

        private static GoogleMaps _instance = null;
        private double latitude, _longitude;
        private int _width, _height, _zoomLevel;
        private readonly String _mapType;
        const String ApiKey = "ABQIAAAAw69_BFiWwuyPWNJOfwkfRxT2yXp_ZAY8_ufC3CFXhHIE1NvwkxT_htBJ1t7aoeF9pkiq0F2o7vd0-Q";

        //these 2 properties will be used with map scrolling methods. You can remove them if not needed
        private const int Offset = 268435456;
        private readonly double _radius;

        private GoogleMaps()
        {
            latitude = -7.2270155;
            _longitude = -35.8846521;
            _zoomLevel = 14;
            _mapType = "satellite";
            _width = 240;
            _height = 268;
            _radius = Offset/Math.PI;
        }

        public static GoogleMaps GetInstance()
        {
            if (_instance == null)
            {
                _instance = new GoogleMaps();
            }
            return _instance;
        }
        
        public Bitmap RetrieveStaticMap()
        {
            String url = getMapUrl();
            
            Uri uri = new Uri(url);

            HttpWebRequest httpRequest = (HttpWebRequest)HttpWebRequest.Create(uri);

            HttpWebResponse httpResponse = (HttpWebResponse)httpRequest.GetResponse();
            Stream imageStream = httpResponse.GetResponseStream();
            Bitmap buddyIcon = new Bitmap(imageStream);
            httpResponse.Close();
            imageStream.Close();

            return buddyIcon;
        }

        
        public Bitmap RetrieveStaticMap(double lat, double lng)
        {
            setLatitude(lat);
            setLongitude(lng);

            String url = getMapUrl(); 
            
            Uri uri = new Uri(url);

            HttpWebRequest httpRequest = (HttpWebRequest)HttpWebRequest.Create(uri);

            HttpWebResponse httpResponse = (HttpWebResponse)httpRequest.GetResponse();
            Stream imageStream = httpResponse.GetResponseStream();
            Bitmap buddyIcon = new Bitmap(imageStream);
            httpResponse.Close();
            imageStream.Close();

            return buddyIcon;
        }


        /*
        public Bitmap getMap(String url)
        {
            Uri uri = new Uri(url);

            HttpWebRequest httpRequest = (HttpWebRequest)HttpWebRequest.Create(uri);

            HttpWebResponse httpResponse = (HttpWebResponse)httpRequest.GetResponse();
            Stream imageStream = httpResponse.GetResponseStream();
            Bitmap buddyIcon = new Bitmap(imageStream);
            httpResponse.Close();
            imageStream.Close();

            return buddyIcon;
        }
        */

        public String getMapUrl()
        {
            String latitudeString = String.Format("{0:0.#######}", latitude);
            String longitudeString = String.Format("{0:0.#######}", _longitude);

            String latitudeFormatted = latitudeString.Replace(',', '.');
            String longitudeFormatted = longitudeString.Replace(',', '.');

            return "http://maps.google.com/staticmap?center=" +
                    latitudeFormatted + "," + longitudeFormatted + "&&&zoom=" + 
                    _zoomLevel + "&&&size=" + _width + "x" + _height + "&&&maptype=" + 
                    _mapType + "&&&mobile=true" + "&&&key=" + ApiKey;
        }

        
        public Bitmap zoomIn()
        {
            _zoomLevel++;
            double[] coords = adjust(0, 0);
            return RetrieveStaticMap(coords[0], coords[1]);
        }

        public Bitmap zoomOut()
        {
            _zoomLevel--;
            double[] coords = adjust(0, 0);
            return RetrieveStaticMap(coords[0], coords[1]);
        }

        public Bitmap panLeft()
        {
            double[] coords = adjust(-_width / 2, 0);
            return RetrieveStaticMap(coords[0], coords[1]);
        }

        public Bitmap panRight()
        {
            double[] coords = adjust(_width / 2, 0);
            return RetrieveStaticMap(coords[0], coords[1]);
        }

        public Bitmap panUp()
        {
            double[] coords = adjust(0, -_height / 2);
            return RetrieveStaticMap(coords[0], coords[1]);
        }

        public Bitmap panDown()
        {
            double[] coords = adjust(0, _height / 2);
            return RetrieveStaticMap(coords[0], coords[1]);
        }

        double[] adjust(int deltaX, int deltaY)
        {
            double longAdjusted = adjustLongitude(deltaX);
            double latAdjusted = adjustLatitude(deltaY);
            return new double[] { latAdjusted, longAdjusted };
        }

        private double adjustLatitude(int deltaY)
        {
            return YToL(LToY(latitude) + (deltaY << (21 - _zoomLevel)));
        }

        private double adjustLongitude(int deltaX)
        {
            return XToL(LToX(_longitude) + (deltaX << (21 - _zoomLevel)));
        }

        double LToX(double x)
        {
            return round(Offset + _radius * x * Math.PI / 180);
        }

        double LToY(double y)
        {
            return round(
                    Offset - _radius *
                    Math.Log(
                    (1 + Math.Sin(y * Math.PI / 180)) /
                    (1 - Math.Sin(y * Math.PI / 180))) / 2);
        }

        double XToL(double x)
        {
            return ((round(x) - Offset) / _radius) * 180 / Math.PI;
        }

        double YToL(double y)
        {
            return (Math.PI / 2 - 2 *
                    Math.Atan(
                    Math.Exp((round(y) - Offset) / _radius))) * 180 / Math.PI;
        }

        double round(double num)
        {
            double floor = Math.Floor(num);

            if (num - floor >= 0.5)
            {
                return Math.Ceiling(num);
            }
            else
            {
                return floor;
            }
        }
        

        public int getHeight()
        {
            return _height;
        }

        public void setHeight(int height)
        {
            this._height = height;
        }

        public int getWidth()
        {
            return _width;
        }

        public void setWidth(int width)
        {
            this._width = width;
        }

        public double getLatitude()
        {
            return this.latitude;
        }

        public void setLatitude(double lat)
        {
            this.latitude = lat;
        }

        public double getLongitude()
        {
            return this._longitude;
        }

        public void setLongitude(double lng)
        {
            this._longitude = lng;
        }

    }
}

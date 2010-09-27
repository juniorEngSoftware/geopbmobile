namespace GPS_StaticMap
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;
        private System.Windows.Forms.MainMenu mainMenu1;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.mainMenu1 = new System.Windows.Forms.MainMenu();
            this.exitMenu = new System.Windows.Forms.MenuItem();
            this.mainMenu = new System.Windows.Forms.MenuItem();
            this.begMenu = new System.Windows.Forms.MenuItem();
            this.mapMenu = new System.Windows.Forms.MenuItem();
            this.zoomInMenu = new System.Windows.Forms.MenuItem();
            this.zoomOutMenu = new System.Windows.Forms.MenuItem();
            this.menuItemGPS = new System.Windows.Forms.MenuItem();
            this.mapBox = new System.Windows.Forms.PictureBox();
            this.begMes = new System.Windows.Forms.Label();
            this.begLat = new System.Windows.Forms.TextBox();
            this.begLng = new System.Windows.Forms.TextBox();
            this.statusBar1 = new System.Windows.Forms.StatusBar();
            this.SuspendLayout();
            // 
            // mainMenu1
            // 
            this.mainMenu1.MenuItems.Add(this.exitMenu);
            this.mainMenu1.MenuItems.Add(this.mainMenu);
            // 
            // exitMenu
            // 
            this.exitMenu.Text = "Sair";
            this.exitMenu.Click += new System.EventHandler(this.menuItem1_Click);
            // 
            // mainMenu
            // 
            this.mainMenu.MenuItems.Add(this.begMenu);
            this.mainMenu.MenuItems.Add(this.mapMenu);
            this.mainMenu.MenuItems.Add(this.zoomInMenu);
            this.mainMenu.MenuItems.Add(this.zoomOutMenu);
            this.mainMenu.MenuItems.Add(this.menuItemGPS);
            this.mainMenu.Text = "Menu";
            this.mainMenu.Click += new System.EventHandler(this.menuItem2_Click);
            // 
            // begMenu
            // 
            this.begMenu.Text = "Iniciar";
            this.begMenu.Click += new System.EventHandler(this.menuItem3_Click);
            // 
            // mapMenu
            // 
            this.mapMenu.Enabled = false;
            this.mapMenu.Text = "Ver Mapa";
            this.mapMenu.Click += new System.EventHandler(this.menuItem4_Click);
            // 
            // zoomInMenu
            // 
            this.zoomInMenu.Enabled = false;
            this.zoomInMenu.Text = "Zoom In";
            this.zoomInMenu.Click += new System.EventHandler(this.menuItem5_Click);
            // 
            // zoomOutMenu
            // 
            this.zoomOutMenu.Enabled = false;
            this.zoomOutMenu.Text = "Zoom Out";
            this.zoomOutMenu.Click += new System.EventHandler(this.menuItem6_Click);
            // 
            // menuItemGPS
            // 
            this.menuItemGPS.Enabled = false;
            this.menuItemGPS.Text = "GPS";
            this.menuItemGPS.Click += new System.EventHandler(this.menuItemGPS_Click);
            // 
            // mapBox
            // 
            this.mapBox.Location = new System.Drawing.Point(0, 0);
            this.mapBox.Name = "mapBox";
            this.mapBox.Size = new System.Drawing.Size(240, 268);
            this.mapBox.Visible = false;
            // 
            // begMes
            // 
            this.begMes.Location = new System.Drawing.Point(21, 48);
            this.begMes.Name = "begMes";
            this.begMes.Size = new System.Drawing.Size(195, 108);
            this.begMes.Text = "label1";
            // 
            // begLat
            // 
            this.begLat.Location = new System.Drawing.Point(0, 0);
            this.begLat.Name = "begLat";
            this.begLat.Size = new System.Drawing.Size(100, 21);
            this.begLat.TabIndex = 2;
            this.begLat.Visible = false;
            // 
            // begLng
            // 
            this.begLng.Location = new System.Drawing.Point(139, 0);
            this.begLng.Name = "begLng";
            this.begLng.Size = new System.Drawing.Size(100, 21);
            this.begLng.TabIndex = 3;
            this.begLng.Visible = false;
            // 
            // statusBar1
            // 
            this.statusBar1.Location = new System.Drawing.Point(0, 268);
            this.statusBar1.Name = "statusBar1";
            this.statusBar1.Size = new System.Drawing.Size(240, 22);
            this.statusBar1.Text = "statusBar1";
            this.statusBar1.Visible = false;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(96F, 96F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(240, 268);
            this.Controls.Add(this.statusBar1);
            this.Controls.Add(this.begLng);
            this.Controls.Add(this.begMes);
            this.Controls.Add(this.begLat);
            this.Controls.Add(this.mapBox);
            this.KeyPreview = true;
            this.Menu = this.mainMenu1;
            this.Name = "Form1";
            this.Text = "Form1";
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.Form1_KeyDown);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.MenuItem exitMenu;
        private System.Windows.Forms.MenuItem mainMenu;
        private System.Windows.Forms.MenuItem begMenu;
        private System.Windows.Forms.PictureBox mapBox;
        private System.Windows.Forms.Label begMes;
        private System.Windows.Forms.TextBox begLat;
        private System.Windows.Forms.TextBox begLng;
        private System.Windows.Forms.MenuItem mapMenu;
        private System.Windows.Forms.MenuItem zoomInMenu;
        private System.Windows.Forms.MenuItem zoomOutMenu;
        private System.Windows.Forms.MenuItem menuItemGPS;
        private System.Windows.Forms.StatusBar statusBar1;
    }
}


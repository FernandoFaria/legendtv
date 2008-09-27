namespace LegendTV
{
    partial class SetupWizard
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SetupWizard));
            this.nextBtn = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1DisplayDevice = new System.Windows.Forms.TabPage();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.radioButton4 = new System.Windows.Forms.RadioButton();
            this.radioButton5 = new System.Windows.Forms.RadioButton();
            this.radioButton6 = new System.Windows.Forms.RadioButton();
            this.radioButton7 = new System.Windows.Forms.RadioButton();
            this.radioButton8 = new System.Windows.Forms.RadioButton();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.tabPage2CalibrateBrightness = new System.Windows.Forms.TabPage();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.tabPage3CalibrateContrast = new System.Windows.Forms.TabPage();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.tabPage4TunerDetection = new System.Windows.Forms.TabPage();
            this.tabPage5TunerSetup = new System.Windows.Forms.TabPage();
            this.radioButton3 = new System.Windows.Forms.RadioButton();
            this.prevBtn = new System.Windows.Forms.Button();
            this.headingLabel = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.listBox1 = new System.Windows.Forms.ListBox();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.tabControl1.SuspendLayout();
            this.tabPage1DisplayDevice.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.tabPage2CalibrateBrightness.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.tabPage3CalibrateContrast.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            this.tabPage4TunerDetection.SuspendLayout();
            this.tabPage5TunerSetup.SuspendLayout();
            this.SuspendLayout();
            // 
            // nextBtn
            // 
            this.nextBtn.Location = new System.Drawing.Point(486, 280);
            this.nextBtn.Margin = new System.Windows.Forms.Padding(3, 0, 0, 0);
            this.nextBtn.Name = "nextBtn";
            this.nextBtn.Size = new System.Drawing.Size(137, 37);
            this.nextBtn.TabIndex = 9;
            this.nextBtn.Text = "Next Step >";
            this.nextBtn.UseVisualStyleBackColor = true;
            this.nextBtn.Click += new System.EventHandler(this.nextBtn_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(9, 280);
            this.button1.Margin = new System.Windows.Forms.Padding(0);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(137, 37);
            this.button1.TabIndex = 10;
            this.button1.Text = "Exit System Setup";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // tabControl1
            // 
            this.tabControl1.Appearance = System.Windows.Forms.TabAppearance.FlatButtons;
            this.tabControl1.Controls.Add(this.tabPage1DisplayDevice);
            this.tabControl1.Controls.Add(this.tabPage2CalibrateBrightness);
            this.tabControl1.Controls.Add(this.tabPage3CalibrateContrast);
            this.tabControl1.Controls.Add(this.tabPage4TunerDetection);
            this.tabControl1.Controls.Add(this.tabPage5TunerSetup);
            this.tabControl1.ItemSize = new System.Drawing.Size(0, 1);
            this.tabControl1.Location = new System.Drawing.Point(9, 52);
            this.tabControl1.Margin = new System.Windows.Forms.Padding(0);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(614, 228);
            this.tabControl1.SizeMode = System.Windows.Forms.TabSizeMode.Fixed;
            this.tabControl1.TabIndex = 11;
            this.tabControl1.SelectedIndexChanged += new System.EventHandler(this.tabControl1_SelectedIndexChanged);
            // 
            // tabPage1DisplayDevice
            // 
            this.tabPage1DisplayDevice.Controls.Add(this.tableLayoutPanel2);
            this.tabPage1DisplayDevice.Controls.Add(this.label3);
            this.tabPage1DisplayDevice.Controls.Add(this.label2);
            this.tabPage1DisplayDevice.Location = new System.Drawing.Point(4, 5);
            this.tabPage1DisplayDevice.Name = "tabPage1DisplayDevice";
            this.tabPage1DisplayDevice.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1DisplayDevice.Size = new System.Drawing.Size(606, 219);
            this.tabPage1DisplayDevice.TabIndex = 0;
            this.tabPage1DisplayDevice.Text = "Display Device";
            this.tabPage1DisplayDevice.UseVisualStyleBackColor = true;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.ColumnCount = 1;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.Controls.Add(this.radioButton4, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.radioButton5, 0, 2);
            this.tableLayoutPanel2.Controls.Add(this.radioButton6, 0, 3);
            this.tableLayoutPanel2.Controls.Add(this.radioButton7, 0, 1);
            this.tableLayoutPanel2.Controls.Add(this.radioButton8, 0, 4);
            this.tableLayoutPanel2.Location = new System.Drawing.Point(20, 60);
            this.tableLayoutPanel2.Margin = new System.Windows.Forms.Padding(20, 3, 3, 3);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 5;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(353, 115);
            this.tableLayoutPanel2.TabIndex = 10;
            // 
            // radioButton4
            // 
            this.radioButton4.AutoSize = true;
            this.radioButton4.Location = new System.Drawing.Point(3, 3);
            this.radioButton4.Name = "radioButton4";
            this.radioButton4.Size = new System.Drawing.Size(184, 17);
            this.radioButton4.TabIndex = 0;
            this.radioButton4.TabStop = true;
            this.radioButton4.Text = "Standard-definition (SD) television";
            this.radioButton4.UseVisualStyleBackColor = true;
            // 
            // radioButton5
            // 
            this.radioButton5.AutoSize = true;
            this.radioButton5.Location = new System.Drawing.Point(3, 49);
            this.radioButton5.Name = "radioButton5";
            this.radioButton5.Size = new System.Drawing.Size(132, 17);
            this.radioButton5.TabIndex = 1;
            this.radioButton5.TabStop = true;
            this.radioButton5.Text = "CRT Computer monitor";
            this.radioButton5.UseVisualStyleBackColor = true;
            // 
            // radioButton6
            // 
            this.radioButton6.AutoSize = true;
            this.radioButton6.Location = new System.Drawing.Point(3, 72);
            this.radioButton6.Name = "radioButton6";
            this.radioButton6.Size = new System.Drawing.Size(131, 17);
            this.radioButton6.TabIndex = 2;
            this.radioButton6.TabStop = true;
            this.radioButton6.Text = "LCD Computer monitor";
            this.radioButton6.UseVisualStyleBackColor = true;
            // 
            // radioButton7
            // 
            this.radioButton7.AutoSize = true;
            this.radioButton7.Location = new System.Drawing.Point(3, 26);
            this.radioButton7.Name = "radioButton7";
            this.radioButton7.Size = new System.Drawing.Size(164, 17);
            this.radioButton7.TabIndex = 4;
            this.radioButton7.TabStop = true;
            this.radioButton7.Text = "High-definition (HD) television";
            this.radioButton7.UseVisualStyleBackColor = true;
            // 
            // radioButton8
            // 
            this.radioButton8.AutoSize = true;
            this.radioButton8.Location = new System.Drawing.Point(3, 95);
            this.radioButton8.Name = "radioButton8";
            this.radioButton8.Size = new System.Drawing.Size(67, 17);
            this.radioButton8.TabIndex = 5;
            this.radioButton8.TabStop = true;
            this.radioButton8.Text = "Projector";
            this.radioButton8.UseVisualStyleBackColor = true;
            // 
            // label3
            // 
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(3, 178);
            this.label3.Margin = new System.Windows.Forms.Padding(0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(600, 38);
            this.label3.TabIndex = 9;
            this.label3.Text = "If you use more than one display device, choose the one you will be using most of" +
                "ten with LegendTV.";
            // 
            // label2
            // 
            this.label2.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(3, 3);
            this.label2.Margin = new System.Windows.Forms.Padding(0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(596, 54);
            this.label2.TabIndex = 8;
            this.label2.Text = "Welcome to LegendTV! This wizard will help you setup your system.\r\n\r\nTo start wit" +
                "h, what type of display device are you using?";
            // 
            // tabPage2CalibrateBrightness
            // 
            this.tabPage2CalibrateBrightness.Controls.Add(this.pictureBox1);
            this.tabPage2CalibrateBrightness.Controls.Add(this.label1);
            this.tabPage2CalibrateBrightness.Controls.Add(this.label4);
            this.tabPage2CalibrateBrightness.Location = new System.Drawing.Point(4, 5);
            this.tabPage2CalibrateBrightness.Name = "tabPage2CalibrateBrightness";
            this.tabPage2CalibrateBrightness.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2CalibrateBrightness.Size = new System.Drawing.Size(606, 219);
            this.tabPage2CalibrateBrightness.TabIndex = 1;
            this.tabPage2CalibrateBrightness.Text = "Black Level";
            this.tabPage2CalibrateBrightness.UseVisualStyleBackColor = true;
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(395, 95);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(205, 121);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage;
            this.pictureBox1.TabIndex = 11;
            this.pictureBox1.TabStop = false;
            // 
            // label1
            // 
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(6, 125);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(383, 91);
            this.label1.TabIndex = 10;
            this.label1.Text = resources.GetString("label1.Text");
            // 
            // label4
            // 
            this.label4.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(3, 3);
            this.label4.Margin = new System.Windows.Forms.Padding(0);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(600, 122);
            this.label4.TabIndex = 9;
            this.label4.Text = resources.GetString("label4.Text");
            // 
            // tabPage3CalibrateContrast
            // 
            this.tabPage3CalibrateContrast.Controls.Add(this.pictureBox2);
            this.tabPage3CalibrateContrast.Controls.Add(this.label5);
            this.tabPage3CalibrateContrast.Controls.Add(this.label6);
            this.tabPage3CalibrateContrast.Location = new System.Drawing.Point(4, 5);
            this.tabPage3CalibrateContrast.Name = "tabPage3CalibrateContrast";
            this.tabPage3CalibrateContrast.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage3CalibrateContrast.Size = new System.Drawing.Size(606, 219);
            this.tabPage3CalibrateContrast.TabIndex = 2;
            this.tabPage3CalibrateContrast.Text = "Contrast";
            this.tabPage3CalibrateContrast.UseVisualStyleBackColor = true;
            // 
            // pictureBox2
            // 
            this.pictureBox2.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox2.Image")));
            this.pictureBox2.Location = new System.Drawing.Point(395, 95);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(205, 121);
            this.pictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage;
            this.pictureBox2.TabIndex = 14;
            this.pictureBox2.TabStop = false;
            // 
            // label5
            // 
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(6, 95);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(383, 121);
            this.label5.TabIndex = 13;
            this.label5.Text = "1. Turn the contrast of your screen to its minimum setting.\r\n2. Slowly increase t" +
                "he contrast setting until the star in the image to the right just blends into th" +
                "e background.";
            // 
            // label6
            // 
            this.label6.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(3, 3);
            this.label6.Margin = new System.Windows.Forms.Padding(0);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(600, 92);
            this.label6.TabIndex = 12;
            this.label6.Text = "Now, we\'ll calibrate the contrast or \"white level\", which controls how light the " +
                "lightest part of an image appears on your screen.\r\n\r\n\r\nFollow these steps to cal" +
                "ibrate it:";
            // 
            // tabPage4TunerDetection
            // 
            this.tabPage4TunerDetection.Controls.Add(this.label7);
            this.tabPage4TunerDetection.Location = new System.Drawing.Point(4, 5);
            this.tabPage4TunerDetection.Name = "tabPage4TunerDetection";
            this.tabPage4TunerDetection.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage4TunerDetection.Size = new System.Drawing.Size(606, 219);
            this.tabPage4TunerDetection.TabIndex = 4;
            this.tabPage4TunerDetection.Text = "Detection";
            this.tabPage4TunerDetection.UseVisualStyleBackColor = true;
            // 
            // tabPage5TunerSetup
            // 
            this.tabPage5TunerSetup.Controls.Add(this.listBox1);
            this.tabPage5TunerSetup.Controls.Add(this.label8);
            this.tabPage5TunerSetup.Location = new System.Drawing.Point(4, 5);
            this.tabPage5TunerSetup.Name = "tabPage5TunerSetup";
            this.tabPage5TunerSetup.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage5TunerSetup.Size = new System.Drawing.Size(606, 219);
            this.tabPage5TunerSetup.TabIndex = 5;
            this.tabPage5TunerSetup.Text = "Tuner Setup";
            this.tabPage5TunerSetup.UseVisualStyleBackColor = true;
            // 
            // radioButton3
            // 
            this.radioButton3.AutoSize = true;
            this.radioButton3.Location = new System.Drawing.Point(3, 72);
            this.radioButton3.Name = "radioButton3";
            this.radioButton3.Size = new System.Drawing.Size(131, 17);
            this.radioButton3.TabIndex = 2;
            this.radioButton3.TabStop = true;
            this.radioButton3.Text = "LCD Computer monitor";
            this.radioButton3.UseVisualStyleBackColor = true;
            // 
            // prevBtn
            // 
            this.prevBtn.Location = new System.Drawing.Point(343, 280);
            this.prevBtn.Margin = new System.Windows.Forms.Padding(0, 0, 3, 0);
            this.prevBtn.Name = "prevBtn";
            this.prevBtn.Size = new System.Drawing.Size(137, 37);
            this.prevBtn.TabIndex = 12;
            this.prevBtn.Text = "< Previous Step";
            this.prevBtn.UseVisualStyleBackColor = true;
            this.prevBtn.Visible = false;
            this.prevBtn.Click += new System.EventHandler(this.prevBtn_Click);
            // 
            // headingLabel
            // 
            this.headingLabel.Font = new System.Drawing.Font("Consolas", 26.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.headingLabel.Location = new System.Drawing.Point(9, 9);
            this.headingLabel.Margin = new System.Windows.Forms.Padding(0);
            this.headingLabel.Name = "headingLabel";
            this.headingLabel.Size = new System.Drawing.Size(614, 43);
            this.headingLabel.TabIndex = 13;
            this.headingLabel.Text = "LegendTV Setup - Name of Page";
            // 
            // label7
            // 
            this.label7.Location = new System.Drawing.Point(3, 3);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(597, 124);
            this.label7.TabIndex = 0;
            this.label7.Tag = "1";
            this.label7.Text = "LegendTV is currently scanning your system to detect your capture devices, which " +
                "enable you to record and watch television.\r\n\r\nThis process should take approxima" +
                "tely 3 minutes.\r\n\r\nPlease wait...";
            // 
            // label8
            // 
            this.label8.Location = new System.Drawing.Point(6, 3);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(594, 33);
            this.label8.TabIndex = 1;
            this.label8.Tag = "1";
            this.label8.Text = "LegendTV has detected the following cards.\r\nPlease select the first card you woul" +
                "d like to set-up and click \"Next Step\".";
            // 
            // listBox1
            // 
            this.listBox1.FormattingEnabled = true;
            this.listBox1.Items.AddRange(new object[] {
            "Hauppage WinTV PVR-500 MCE #1",
            "Hauppage WinTV PVR-500 MCE #2",
            "pcHDTV HD-3000 High-Definition Tuner",
            "Air2PC High-Definition Tuner"});
            this.listBox1.Location = new System.Drawing.Point(6, 39);
            this.listBox1.Name = "listBox1";
            this.listBox1.Size = new System.Drawing.Size(594, 173);
            this.listBox1.TabIndex = 2;
            // 
            // timer1
            // 
            this.timer1.Interval = 10000;
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // SetupWizard
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(632, 326);
            this.Controls.Add(this.headingLabel);
            this.Controls.Add(this.prevBtn);
            this.Controls.Add(this.tabControl1);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.nextBtn);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "SetupWizard";
            this.Text = "LegendTV";
            this.tabControl1.ResumeLayout(false);
            this.tabPage1DisplayDevice.ResumeLayout(false);
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel2.PerformLayout();
            this.tabPage2CalibrateBrightness.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.tabPage3CalibrateContrast.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            this.tabPage4TunerDetection.ResumeLayout(false);
            this.tabPage5TunerSetup.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button nextBtn;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1DisplayDevice;
        private System.Windows.Forms.TabPage tabPage2CalibrateBrightness;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
        private System.Windows.Forms.RadioButton radioButton4;
        private System.Windows.Forms.RadioButton radioButton5;
        private System.Windows.Forms.RadioButton radioButton6;
        private System.Windows.Forms.RadioButton radioButton7;
        private System.Windows.Forms.RadioButton radioButton8;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.RadioButton radioButton3;
        private System.Windows.Forms.Button prevBtn;
        private System.Windows.Forms.TabPage tabPage3CalibrateContrast;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label headingLabel;
        private System.Windows.Forms.TabPage tabPage4TunerDetection;
        private System.Windows.Forms.TabPage tabPage5TunerSetup;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.PictureBox pictureBox2;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.ListBox listBox1;
        private System.Windows.Forms.Timer timer1;

    }
}


namespace LegendTV
{
    partial class MainMenu
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
            this.tvButton = new System.Windows.Forms.Button();
            this.videoButton = new System.Windows.Forms.Button();
            this.settingsButton = new System.Windows.Forms.Button();
            this.musicButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.exitButton = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // tvButton
            // 
            this.tvButton.Location = new System.Drawing.Point(236, 55);
            this.tvButton.Name = "tvButton";
            this.tvButton.Size = new System.Drawing.Size(160, 70);
            this.tvButton.TabIndex = 0;
            this.tvButton.Text = "TV";
            this.tvButton.UseVisualStyleBackColor = true;
            this.tvButton.Click += new System.EventHandler(this.tvButton_Click);
            // 
            // videoButton
            // 
            this.videoButton.Location = new System.Drawing.Point(402, 131);
            this.videoButton.Name = "videoButton";
            this.videoButton.Size = new System.Drawing.Size(160, 70);
            this.videoButton.TabIndex = 1;
            this.videoButton.Text = "Videos";
            this.videoButton.UseVisualStyleBackColor = true;
            // 
            // settingsButton
            // 
            this.settingsButton.Location = new System.Drawing.Point(236, 207);
            this.settingsButton.Name = "settingsButton";
            this.settingsButton.Size = new System.Drawing.Size(160, 70);
            this.settingsButton.TabIndex = 2;
            this.settingsButton.Text = "Settings";
            this.settingsButton.UseVisualStyleBackColor = true;
            // 
            // musicButton
            // 
            this.musicButton.Location = new System.Drawing.Point(70, 131);
            this.musicButton.Name = "musicButton";
            this.musicButton.Size = new System.Drawing.Size(160, 70);
            this.musicButton.TabIndex = 3;
            this.musicButton.Text = "Music";
            this.musicButton.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.Font = new System.Drawing.Font("Consolas", 26.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(9, 9);
            this.label1.Margin = new System.Windows.Forms.Padding(0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(614, 43);
            this.label1.TabIndex = 4;
            this.label1.Text = "LegendTV";
            // 
            // exitButton
            // 
            this.exitButton.Location = new System.Drawing.Point(9, 280);
            this.exitButton.Margin = new System.Windows.Forms.Padding(0);
            this.exitButton.Name = "exitButton";
            this.exitButton.Size = new System.Drawing.Size(137, 37);
            this.exitButton.TabIndex = 5;
            this.exitButton.Text = "Exit LegendTV";
            this.exitButton.UseVisualStyleBackColor = true;
            this.exitButton.Click += new System.EventHandler(this.exitButton_Click);
            // 
            // label2
            // 
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(149, 280);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(471, 37);
            this.label2.TabIndex = 6;
            this.label2.Text = "Now recording \"Family Guy\" on FOX";
            this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // label3
            // 
            this.label3.Font = new System.Drawing.Font("Consolas", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(257, 151);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(118, 24);
            this.label3.TabIndex = 7;
            this.label3.Text = "Main Menu";
            this.label3.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // MainMenu
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(632, 326);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.exitButton);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.musicButton);
            this.Controls.Add(this.settingsButton);
            this.Controls.Add(this.videoButton);
            this.Controls.Add(this.tvButton);
            this.Name = "MainMenu";
            this.Text = "LegendTV";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button tvButton;
        private System.Windows.Forms.Button videoButton;
        private System.Windows.Forms.Button settingsButton;
        private System.Windows.Forms.Button musicButton;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button exitButton;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;

    }
}


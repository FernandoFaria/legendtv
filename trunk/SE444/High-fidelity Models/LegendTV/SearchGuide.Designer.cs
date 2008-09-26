namespace LegendTV
{
    partial class SearchGuide
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
            this.searchField = new System.Windows.Forms.TextBox();
            this.searchFieldLabel = new System.Windows.Forms.Label();
            this.programList = new System.Windows.Forms.ListBox();
            this.showTimeList = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.descriptionList = new System.Windows.Forms.ListBox();
            this.exitButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // searchField
            // 
            this.searchField.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.searchField.Location = new System.Drawing.Point(166, 47);
            this.searchField.Name = "searchField";
            this.searchField.Size = new System.Drawing.Size(454, 24);
            this.searchField.TabIndex = 0;
            // 
            // searchFieldLabel
            // 
            this.searchFieldLabel.AutoSize = true;
            this.searchFieldLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.searchFieldLabel.Location = new System.Drawing.Point(12, 47);
            this.searchFieldLabel.Name = "searchFieldLabel";
            this.searchFieldLabel.Size = new System.Drawing.Size(148, 18);
            this.searchFieldLabel.TabIndex = 1;
            this.searchFieldLabel.Text = "Enter search term(s):";
            // 
            // programList
            // 
            this.programList.FormattingEnabled = true;
            this.programList.Location = new System.Drawing.Point(16, 90);
            this.programList.Name = "programList";
            this.programList.Size = new System.Drawing.Size(154, 173);
            this.programList.TabIndex = 2;
            // 
            // showTimeList
            // 
            this.showTimeList.FormattingEnabled = true;
            this.showTimeList.Location = new System.Drawing.Point(185, 90);
            this.showTimeList.Name = "showTimeList";
            this.showTimeList.Size = new System.Drawing.Size(120, 173);
            this.showTimeList.TabIndex = 3;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Consolas", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(221, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(190, 24);
            this.label1.TabIndex = 4;
            this.label1.Text = "Search TV Guide";
            // 
            // descriptionList
            // 
            this.descriptionList.FormattingEnabled = true;
            this.descriptionList.Location = new System.Drawing.Point(320, 90);
            this.descriptionList.Name = "descriptionList";
            this.descriptionList.Size = new System.Drawing.Size(300, 225);
            this.descriptionList.TabIndex = 5;
            // 
            // exitButton
            // 
            this.exitButton.Location = new System.Drawing.Point(9, 280);
            this.exitButton.Margin = new System.Windows.Forms.Padding(0);
            this.exitButton.Name = "exitButton";
            this.exitButton.Size = new System.Drawing.Size(137, 37);
            this.exitButton.TabIndex = 6;
            this.exitButton.Text = "Back to TV Menu";
            this.exitButton.UseVisualStyleBackColor = true;
            // 
            // SearchGuide
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(632, 326);
            this.Controls.Add(this.exitButton);
            this.Controls.Add(this.descriptionList);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.showTimeList);
            this.Controls.Add(this.programList);
            this.Controls.Add(this.searchFieldLabel);
            this.Controls.Add(this.searchField);
            this.Name = "SearchGuide";
            this.Text = "SearchGuide";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox searchField;
        private System.Windows.Forms.Label searchFieldLabel;
        private System.Windows.Forms.ListBox programList;
        private System.Windows.Forms.ListBox showTimeList;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ListBox descriptionList;
        private System.Windows.Forms.Button exitButton;
    }
}
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SearchGuide));
            this.searchField = new System.Windows.Forms.TextBox();
            this.searchFieldLabel = new System.Windows.Forms.Label();
            this.programList = new System.Windows.Forms.ListBox();
            this.showTimeList = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.exitButton = new System.Windows.Forms.Button();
            this.description = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // searchField
            // 
            this.searchField.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.searchField.Location = new System.Drawing.Point(166, 47);
            this.searchField.Name = "searchField";
            this.searchField.Size = new System.Drawing.Size(454, 24);
            this.searchField.TabIndex = 0;
            this.searchField.Text = "CSI";
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
            this.programList.Items.AddRange(new object[] {
            "CSI: Crime Scene Investigation",
            "CSI: Miami",
            "CSI: RIT",
            "CSI: Wasilla"});
            this.programList.Location = new System.Drawing.Point(16, 90);
            this.programList.Name = "programList";
            this.programList.Size = new System.Drawing.Size(154, 173);
            this.programList.TabIndex = 2;
            // 
            // showTimeList
            // 
            this.showTimeList.FormattingEnabled = true;
            this.showTimeList.Items.AddRange(new object[] {
            "Mon  09/29/08 9:00 PM  CBS",
            "Tue 09/29/08  10:00 PM CBS",
            "Fri   10/03/08   5:00 PM  USA",
            "Mon  10/06/08 9:00 PM  CBS",
            "Tue 10/07/08  10:00 PM CBS",
            "Fri   10/10/08   5:00 PM  USA",
            "Mon  10/13/08 9:00 PM  CBS",
            "Tue 10/14/08  10:00 PM CBS"});
            this.showTimeList.Location = new System.Drawing.Point(185, 90);
            this.showTimeList.Name = "showTimeList";
            this.showTimeList.Size = new System.Drawing.Size(160, 173);
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
            // description
            // 
            this.description.Location = new System.Drawing.Point(351, 90);
            this.description.Multiline = true;
            this.description.Name = "description";
            this.description.Size = new System.Drawing.Size(269, 224);
            this.description.TabIndex = 7;
            this.description.Text = resources.GetString("description.Text");
            // 
            // SearchGuide
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(632, 326);
            this.Controls.Add(this.description);
            this.Controls.Add(this.exitButton);
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
        private System.Windows.Forms.Button exitButton;
        private System.Windows.Forms.TextBox description;
    }
}
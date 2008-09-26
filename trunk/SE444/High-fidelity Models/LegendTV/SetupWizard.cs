using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace LegendTV
{
    public partial class SetupWizard : Form
    {
        public SetupWizard()
        {
            InitializeComponent();

            tabControl1_SelectedIndexChanged(this, null);
        }

        private void prevBtn_Click(object sender, EventArgs e)
        {
            --tabControl1.SelectedIndex;
        }

        private void nextBtn_Click(object sender, EventArgs e)
        {
            ++tabControl1.SelectedIndex;
        }

        private void tabControl1_SelectedIndexChanged(object sender, EventArgs e)
        {
            prevBtn.Visible = (tabControl1.SelectedIndex != 0);
            nextBtn.Visible = (tabControl1.SelectedIndex != (tabControl1.TabPages.Count - 1));

            headingLabel.Text = "LegendTV Setup - " + tabControl1.SelectedTab.Text;
        }     
    }
}
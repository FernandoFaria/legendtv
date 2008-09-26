using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace LegendTV
{
    public partial class MainMenu : Form
    {
        public MainMenu()
        {
            InitializeComponent();
        }

        private void tvButton_Click(object sender, EventArgs e)
        {
            TVMenu  otherMenu   = new TVMenu();

            this.Hide();

            otherMenu.Disposed += new EventHandler(otherMenu_Disposed);

            otherMenu.Show();
        }

        void otherMenu_Disposed(object sender, EventArgs e)
        {
            this.Show();
        }

        private void exitButton_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
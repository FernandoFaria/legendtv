using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace LegendTV
{
    public partial class FormRunner : Form
    {
        public FormRunner()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            new MainMenu().Show();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            new Guide().Show();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            new OnScreenKeyboard().Show();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            new Playback().Show();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            new SearchGuide().Show();
        }

        private void button6_Click(object sender, EventArgs e)
        {
            new SetupWizard().Show();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            new TVMenu().Show();
        }
    }
}
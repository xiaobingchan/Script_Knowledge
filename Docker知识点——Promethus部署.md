sudo useradd --no-create-home --shell /usr/sbin/nologin prometheus
sudo useradd --no-create-home --shell /bin/false node_exporter

sudo mkdir /etc/prometheus
sudo mkdir /var/lib/prometheus

sudo chown prometheus:prometheus /etc/prometheus
sudo chown prometheus:prometheus /var/lib/prometheus

tar -xzvf node_exporter-0.18.1.linux-amd64.tar.gz
sudo cp node_exporter-0.18.1.linux-amd64/node_exporter /usr/local/bin
sudo chown node_exporter:node_exporter /usr/local/bin/node_exporter

cat >> /etc/systemd/system/node_exporter.service << EOF
[Unit]
Description=Node Exporter
Wants=network-online.target
After=network-online.target
[Service]
User=node_exporter
Group=node_exporter
Type=simple
ExecStart=/usr/local/bin/node_exporter
[Install]
WantedBy=multi-user.target
EOF
systemctl daemon-reload
systemctl start node_exporter
systemctl enable node_exporter

###################################################################

tar -xzvf prometheus-2.10.0-rc.0.linux-amd64.tar.gz
cd prometheus-2.10.0-rc.0.linux-amd64
cp ./prometheus /usr/local/bin/
cp ./promtool /usr/local/bin/
chown prometheus:prometheus /usr/local/bin/prometheus
chown prometheus:prometheus /usr/local/bin/promtool
cp -r ./consoles /etc/prometheus
cp -r ./console_libraries /etc/prometheus
chown -R prometheus:prometheus /etc/prometheus/consoles
chown -R prometheus:prometheus /etc/prometheus/console_libraries
cat > /etc/prometheus/prometheus.yml << EOF
global:
  scrape_interval:     15s
  evaluation_interval: 15s

rule_files:

scrape_configs:
- job_name: 'prometheus'
  scrape_interval: 5s
  static_configs:
    - targets: 
	  - localhost:9090
	  
- job_name: 'node_exporter'
  scrape_interval: 5s
  static_configs:
    - targets: 
	  - localhost:9100
EOF
chown prometheus:prometheus /etc/prometheus/prometheus.yml

sudo -u prometheus /usr/local/bin/prometheus --config.file /etc/prometheus/prometheus.yml --storage.tsdb.path /var/lib/prometheus/ --web.console.templates=/etc/prometheus/consoles --web.console.libraries=/etc/prometheus/console_libraries

cat >> /etc/systemd/system/prometheus.service << EOF
[Unit]
  Description=Prometheus Monitoring
  Wants=network-online.target
  After=network-online.target

[Service]
  User=prometheus
  Group=prometheus
  Type=simple
  ExecStart=/usr/local/bin/prometheus \
  --config.file /etc/prometheus/prometheus.yml \
  --storage.tsdb.path /var/lib/prometheus/ \
  --web.console.templates=/etc/prometheus/consoles \
  --web.console.libraries=/etc/prometheus/console_libraries
  ExecReload=/bin/kill -HUP $MAINPID

[Install]
  WantedBy=multi-user.target
EOF
systemctl daemon-reload
systemctl enable prometheus
systemctl start prometheus

rpm -ivh grafana-6.4.3-1.x86_64.rpm
sudo systemctl daemon-reload && sudo systemctl enable grafana-server && sudo systemctl start grafana-server
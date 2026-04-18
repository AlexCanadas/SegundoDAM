import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Lista de productos',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const MyHomePage(title: 'Lista de productos'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final List<Map<String, dynamic>> productos = [
    {"nombre": "Camiseta", "valor": 15.99},
    {"nombre": "Pantalón", "valor": 25.50},
    {"nombre": "Zapatillas", "valor": 60.00},
    {"nombre": "Gorra", "valor": 12.00},
    {"nombre": "Mochila", "valor": 30.99},
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: ListView.builder(
        itemCount: productos.length,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text(productos[index]["nombre"]),
            subtitle: Text("Valor: ${productos[index]["valor"]} €"),
          );
        },
      ),
    );
  }
}
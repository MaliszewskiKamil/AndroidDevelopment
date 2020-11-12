package com.example.level4task1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.level4task1.adapters.ShoppingListAdapter
import com.example.level4task1.data.Product
import com.example.level4task1.database.ProductRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_product_dialog.*
import kotlinx.android.synthetic.main.shopping_list_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ShoppingListFragment : Fragment() {

    private val products = arrayListOf<Product>()
    private val shoppingListAdapter = ShoppingListAdapter(products)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.shopping_list_fragment, container, false)
    }

    private lateinit var productRepository: ProductRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productRepository = ProductRepository(requireContext())
        getProductsFromDatabase()
        initRv()
        fab_add.setOnClickListener{
            showAddProductDialog();
        }

    }

    @SuppressLint("InflateParams")
    private fun showAddProductDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.add_product_dialog_title))
        val dialogLayout = layoutInflater.inflate(R.layout.add_product_dialog, null)
        val productName = dialogLayout.findViewById<EditText>(R.id.txt_product_name)
        val amount = dialogLayout.findViewById<EditText>(R.id.txt_amount)

        builder.setView(dialogLayout)
        builder.setPositiveButton(R.string.dialog_ok_btn) {
            _:DialogInterface, _: Int ->
            addProduct(productName, amount)
        }
        builder.show()
    }

    private fun addProduct(productName: EditText, amount: EditText) {
        if(validateFields(txt_product_name, txt_amount)){
            mainScope.launch {
                val product = Product(
                    productName = txt_product_name.text.toString(),
                    productQuantity = txt_amount.text.toString().toInt()
                )

                withContext(Dispatchers.IO){
                    productRepository.insertProduct(product)
                }

                getProductsFromDatabase()
            }
        }
    }

    private fun validateFields(txtProductName: EditText?, txtAmount: EditText?): Boolean {
        TODO("Not yet implemented")
    }


    private fun initRv() {
        rv_shopping_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_shopping_list.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rv_shopping_list.adapter = shoppingListAdapter
        rv_shopping_list.apply {
            setHasFixedSize(true)
        }


    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val productToDelete = products[position]
                CoroutineScope(Dispatchers.Main).launch {
                    val reminders = withContext(Dispatchers.IO) {
                        productRepository.deleteProduct(productToDelete)
                    }
                    getProductsFromDatabase()
                }
            }

        }
        return ItemTouchHelper(callback)
    }

    private fun getProductsFromDatabase() {
        mainScope.launch {
            val shoppingList = withContext(Dispatchers.IO) {
                productRepository.getAllProducts()
            }
            this@ShoppingListFragment.products.clear()
            this@ShoppingListFragment.products.addAll(products)
            this@ShoppingListFragment.shoppingListAdapter.notifyDataSetChanged()
        }
    }
}